package com.luandev.springbootsecurity.service;

import com.luandev.springbootsecurity.model.user.User;
import com.luandev.springbootsecurity.model.user.dto.request.RegisterRequest;

public interface RegisterService {

    User registerUser(final RegisterRequest registerRequest);

}
