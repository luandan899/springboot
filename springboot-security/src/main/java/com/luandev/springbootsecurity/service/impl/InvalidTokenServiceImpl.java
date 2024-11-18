package com.luandev.springbootsecurity.service.impl;


import com.luandev.springbootsecurity.model.common.exception.BusinessLogicException;
import com.luandev.springbootsecurity.model.user.entity.InvalidTokenEntity;
import com.luandev.springbootsecurity.model.user.enums.ErrorMessage;
import com.luandev.springbootsecurity.repository.InvalidTokenRepository;
import com.luandev.springbootsecurity.service.InvalidTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class InvalidTokenServiceImpl implements InvalidTokenService {

    private final InvalidTokenRepository invalidTokenRepository;

    @Override
    public void invalidateTokens(Set<String> tokenIds) {

        final Set<InvalidTokenEntity> enocaInvalidTokenEntities = tokenIds.stream()
                .map(tokenId -> InvalidTokenEntity.builder()
                        .tokenId(tokenId)
                        .build()
                )
                .collect(Collectors.toSet());

        invalidTokenRepository.saveAll(enocaInvalidTokenEntities);
    }

    @Override
    public void checkForInvalidityOfToken(String tokenId) {

        final boolean isTokenInvalid = invalidTokenRepository.findByTokenId(tokenId).isPresent();

        if (isTokenInvalid) {
            throw BusinessLogicException.builder().errorMessage(ErrorMessage.TOKEN_ALREADY_INVALID).build();
        }

    }

}
