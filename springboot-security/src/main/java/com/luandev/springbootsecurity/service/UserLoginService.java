package com.luandev.springbootsecurity.service;

import com.luandev.springbootsecurity.model.user.Token;
import com.luandev.springbootsecurity.model.user.dto.request.LoginRequest;

public interface UserLoginService {

    Token login(final LoginRequest loginRequest);

}
