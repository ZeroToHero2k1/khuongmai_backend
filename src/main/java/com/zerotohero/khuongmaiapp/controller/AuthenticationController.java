package com.zerotohero.khuongmaiapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.zerotohero.khuongmaiapp.dto.request.*;
import com.zerotohero.khuongmaiapp.dto.response.*;
import com.zerotohero.khuongmaiapp.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    ObjectMapper objectMapper;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.authenticate(request))
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request){
        return ApiResponse.<IntrospectResponse>builder()
                .result(authenticationService.introspect(request))
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody InvalidTokenRequest request) throws ParseException, JOSEException {
        authenticationService.logOut(request);
        return ApiResponse.<Void>builder().message("Đăng xuất thành công").build();
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest request) throws ParseException, JOSEException {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.refreshToken(request))
                .build();
    }

    @PostMapping("/sign")
    public ApiResponse<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest request){
        return ApiResponse.<SignUpResponse>builder()
                .result(authenticationService.SignUp(request))
                .build();
    }

    @PostMapping("/updateinfo")
    public ApiResponse<UpdateMyInfoResponse> updateInfo(@RequestPart("myinfo") String requestJson,@RequestPart(value="image", required = false) MultipartFile file) throws IOException {
        UpdateMyInfoRequest request=objectMapper.readValue(requestJson,UpdateMyInfoRequest.class);
        return ApiResponse.<UpdateMyInfoResponse>builder().result(authenticationService.updateMyInfo(request,file)).build();
    }

}
