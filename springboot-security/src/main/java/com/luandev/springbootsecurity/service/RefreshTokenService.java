package com.luandev.springbootsecurity.service;

import com.luandev.springbootsecurity.model.user.Token;
import com.luandev.springbootsecurity.model.user.dto.request.TokenRefreshRequest;


public interface RefreshTokenService {

    Token refreshToken(final TokenRefreshRequest tokenRefreshRequest);

}
