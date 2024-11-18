package com.luandev.springbootsecurity.service;

import com.luandev.springbootsecurity.model.user.dto.request.TokenInvalidateRequest;

public interface LogoutService {

    void logout(final TokenInvalidateRequest tokenInvalidateRequest);

}
