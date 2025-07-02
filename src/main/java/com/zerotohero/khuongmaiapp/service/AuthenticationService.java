package com.zerotohero.khuongmaiapp.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.zerotohero.khuongmaiapp.dto.request.AuthenticationRequest;
import com.zerotohero.khuongmaiapp.dto.request.IntrospectRequest;
import com.zerotohero.khuongmaiapp.dto.request.InvalidTokenRequest;
import com.zerotohero.khuongmaiapp.dto.request.RefreshTokenRequest;
import com.zerotohero.khuongmaiapp.dto.response.AuthenticationResponse;
import com.zerotohero.khuongmaiapp.dto.response.IntrospectResponse;
import com.zerotohero.khuongmaiapp.entity.InvalidatedToken;
import com.zerotohero.khuongmaiapp.entity.User;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.repository.InvalidatedTokenRepository;
import com.zerotohero.khuongmaiapp.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    UserRepository userRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;



    public String generateToken(User user){
        JWSHeader jwsHeader=new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet=new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("khuongmaiapp.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(VALID_DURATION,ChronoUnit.HOURS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope",buildScope(user))
                .build();

        Payload payload=new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject=new JWSObject(jwsHeader,payload);

        try{
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Không thể tạo token vì",e);
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user){
        StringJoiner stringJoiner=new StringJoiner(" ");
        stringJoiner.add("ROLE_"+user.getRole().getRoleName());
        if(!CollectionUtils.isEmpty(user.getRole().getPermissionSet())){
            user.getRole().getPermissionSet().forEach(permission -> {
                stringJoiner.add(permission.getPermissionName());
            });
        }
        return stringJoiner.toString();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        var user=userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(()->new KMAppException(ErrorCode.USER_NOT_FOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated=passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());

        if(!authenticated)
            throw new KMAppException(ErrorCode.UNAUTHENTICATE);

        return AuthenticationResponse.builder().token(generateToken(user)).authenticated(authenticated).build();
    }

    private SignedJWT verifyToken(String token,boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier=new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT=SignedJWT.parse(token);

        Date expityTime=(isRefresh)//refresh=true thì sẽ cộng thêm thời hạn refresh đã quy ước trong yml
                ?new Date(signedJWT.getJWTClaimsSet().getIssueTime().toInstant().plus(REFRESHABLE_DURATION,ChronoUnit.HOURS).toEpochMilli())
                :signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified=signedJWT.verify(verifier);//kết quả kiểm tra chữ ký xác thực, kiểu boolean
        //Token hết hạn
        if (expityTime.before(new Date())) {
            throw new KMAppException(ErrorCode.TOKEN_EXPIRED);
        }

        if(!verified&&expityTime.after(new Date()))//còn hạn nhưng chữ ký không hợp lệ => lỗi
            throw new KMAppException(ErrorCode.UNAUTHENTICATE);

        //nếu token đã có trong blacklist thì => lỗi
        if(invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new KMAppException(ErrorCode.UNAUTHENTICATE);
        return signedJWT;
    }

    public void logOut(InvalidTokenRequest request) throws ParseException, JOSEException {
        var signToken=verifyToken(request.getToken(),true);
        String jit=signToken.getJWTClaimsSet().getJWTID();
        Date expiryTime=signToken.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken=InvalidatedToken.builder()
                .id(jit)
                .expiryTime(expiryTime)
                .build();

        invalidatedTokenRepository.save(invalidatedToken);
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        //kiểm tra chữ ký+kiểm tra xem còn thời hạn refresh không (cộng dồn tgian refresh trong hàm xác thực)
        // , nếu còn thì mới cho refresh
        var signedJWT=verifyToken(request.getToken(), true);

        var jit=signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime=signedJWT.getJWTClaimsSet().getExpirationTime();
        //bỏ token cũ để chống replay attack
        InvalidatedToken invalidatedToken= InvalidatedToken.builder()
                .id(jit)
                .expiryTime(expiryTime)
                .build();

        invalidatedTokenRepository.save(invalidatedToken);

        var username=signedJWT.getJWTClaimsSet().getSubject();

        var user=userRepository.findByUsername(username).orElseThrow(()->new KMAppException(ErrorCode.USER_NOT_FOUND));

        var token=generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();

    }

    public IntrospectResponse introspect(IntrospectRequest introspectRequest){
        var token=introspectRequest.getToken();
        boolean isValid=true;
        try{
            verifyToken(token,false);
        } catch(KMAppException | JOSEException | ParseException e){
            isValid=false;
        }

        return IntrospectResponse.builder().valid(isValid).build();

    }

}
