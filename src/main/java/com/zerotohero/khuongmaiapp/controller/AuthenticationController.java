package com.zerotohero.khuongmaiapp.controller;

import com.nimbusds.jose.JOSEException;
import com.zerotohero.khuongmaiapp.dto.request.AuthenticationRequest;
import com.zerotohero.khuongmaiapp.dto.request.IntrospectRequest;
import com.zerotohero.khuongmaiapp.dto.request.InvalidTokenRequest;
import com.zerotohero.khuongmaiapp.dto.request.RefreshTokenRequest;
import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.dto.response.AuthenticationResponse;
import com.zerotohero.khuongmaiapp.dto.response.IntrospectResponse;
import com.zerotohero.khuongmaiapp.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

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


}
