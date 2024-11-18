package com.luandev.springbootsecurity.service.impl;

import com.luandev.springbootsecurity.model.common.exception.BusinessLogicException;
import com.luandev.springbootsecurity.model.user.Token;
import com.luandev.springbootsecurity.model.user.dto.request.TokenRefreshRequest;
import com.luandev.springbootsecurity.model.user.entity.UserEntity;
import com.luandev.springbootsecurity.model.user.enums.ErrorMessage;
import com.luandev.springbootsecurity.model.user.enums.TokenClaims;
import com.luandev.springbootsecurity.model.user.enums.UserStatus;
import com.luandev.springbootsecurity.service.RefreshTokenService;
import com.luandev.springbootsecurity.service.TokenService;
import com.luandev.springbootsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final TokenService tokenService;
    private final UserService userService;

    @Override
    public Token refreshToken(TokenRefreshRequest tokenRefreshRequest) {

        tokenService.verifyAndValidate(tokenRefreshRequest.getToken());

        final String adminId = tokenService
                .getPayload(tokenRefreshRequest.getToken())
                .get(TokenClaims.USER_ID.getValue())
                .toString();

        final UserEntity userEntityFromDB = this.userService.getUserById(adminId);

        if (!(UserStatus.ACTIVE.equals(userEntityFromDB.getUserStatus()))) {
            throw BusinessLogicException.builder().errorMessage(ErrorMessage.STATUS_NOT_VALID)
                    .params(List.of(String.valueOf(userEntityFromDB.getUserStatus()))).build();

        }

        return tokenService.generateToken(
                userEntityFromDB.getClaims(),
                tokenRefreshRequest.getToken()
        );

    }

}
