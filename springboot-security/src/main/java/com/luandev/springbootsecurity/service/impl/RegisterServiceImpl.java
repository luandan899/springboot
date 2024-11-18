package com.luandev.springbootsecurity.service.impl;

import com.luandev.springbootsecurity.model.common.exception.BusinessLogicException;
import com.luandev.springbootsecurity.model.user.User;
import com.luandev.springbootsecurity.model.user.dto.request.RegisterRequest;
import com.luandev.springbootsecurity.model.user.entity.UserEntity;
import com.luandev.springbootsecurity.model.user.enums.ErrorMessage;
import com.luandev.springbootsecurity.model.user.mapper.RegisterRequestToUserEntityMapper;
import com.luandev.springbootsecurity.model.user.mapper.UserEntityToUserMapper;
import com.luandev.springbootsecurity.repository.UserRepository;
import com.luandev.springbootsecurity.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;

    private final RegisterRequestToUserEntityMapper registerRequestToUserEntityMapper = RegisterRequestToUserEntityMapper.initialize();

    private final UserEntityToUserMapper userEntityToUserMapper = UserEntityToUserMapper.initialize();

    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterRequest registerRequest) {

        if (userRepository.existsUserEntityByEmail(registerRequest.getEmail())) {
            throw BusinessLogicException.builder()
                    .errorMessage(ErrorMessage.EMAIL_EXISTED)
                    .params(List.of(registerRequest.getEmail()))
                    .build();
        }

        final UserEntity userEntityToBeSave = registerRequestToUserEntityMapper.mapForSaving(registerRequest);

        userEntityToBeSave.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        UserEntity savedUserEntity = userRepository.save(userEntityToBeSave);

        return userEntityToUserMapper.map(savedUserEntity);

    }

}
