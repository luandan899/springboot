package com.luandev.springbootsecurity.controller;

import com.luandev.springbootsecurity.model.common.response.BaseResponse;
import com.luandev.springbootsecurity.model.user.Token;
import com.luandev.springbootsecurity.model.user.dto.request.LoginRequest;
import com.luandev.springbootsecurity.model.user.dto.request.RegisterRequest;
import com.luandev.springbootsecurity.model.user.dto.request.TokenInvalidateRequest;
import com.luandev.springbootsecurity.model.user.dto.request.TokenRefreshRequest;
import com.luandev.springbootsecurity.model.user.dto.response.TokenResponse;
import com.luandev.springbootsecurity.model.user.mapper.TokenToTokenResponseMapper;
import com.luandev.springbootsecurity.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;


@RestController
@RequestMapping("/api/v1/authentication/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final RegisterService registerService;

    private final TokenService tokenService;

    private final UserLoginService userLoginService;

    private final RefreshTokenService refreshTokenService;

    private final LogoutService logoutService;

    private final TokenToTokenResponseMapper tokenToTokenResponseMapper = TokenToTokenResponseMapper.initialize();

    @PostMapping("/register")
    public BaseResponse<Void> registerUser(@RequestBody @Validated final RegisterRequest registerRequest) {
        log.info("UserController | registerUser");
        registerService.registerUser(registerRequest);
        return BaseResponse.success();
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Void> validateToken(@RequestParam String token) {
        log.info("UserController | validateToken");
        tokenService.verifyAndValidate(token);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/login")
    public BaseResponse<TokenResponse> loginUser(@RequestBody @Valid final LoginRequest loginRequest) {
        log.info("UserController | validateToken");
        final Token token = userLoginService.login(loginRequest);
        final TokenResponse tokenResponse = tokenToTokenResponseMapper.map(token);
        return BaseResponse.successOf(tokenResponse);
    }

    @PostMapping("/refresh-token")
    public BaseResponse<TokenResponse> refreshToken(@RequestBody @Valid final TokenRefreshRequest tokenRefreshRequest) {
        log.info("UserController | refreshToken");
        final Token token = refreshTokenService.refreshToken(tokenRefreshRequest);
        final TokenResponse tokenResponse = tokenToTokenResponseMapper.map(token);
        return BaseResponse.successOf(tokenResponse);
    }

    @PostMapping("/logout")
    public BaseResponse<Void> logout(@RequestBody @Valid final TokenInvalidateRequest tokenInvalidateRequest) {
        log.info("UserController | logout");
        logoutService.logout(tokenInvalidateRequest);
        return BaseResponse.success();
    }


    @GetMapping("/authenticate")
    public ResponseEntity<UsernamePasswordAuthenticationToken> getAuthentication(@RequestParam String token) {
        UsernamePasswordAuthenticationToken authentication = tokenService.getAuthentication(token);
        return ResponseEntity.ok(authentication);
    }

}
