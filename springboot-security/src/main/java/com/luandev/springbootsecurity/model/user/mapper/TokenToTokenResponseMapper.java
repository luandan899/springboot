package com.luandev.springbootsecurity.model.user.mapper;

import com.luandev.springbootsecurity.model.common.mapper.BaseMapper;
import com.luandev.springbootsecurity.model.user.Token;
import com.luandev.springbootsecurity.model.user.dto.response.TokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface TokenToTokenResponseMapper extends BaseMapper<Token, TokenResponse> {


    @Override
    TokenResponse map(Token source);

    static TokenToTokenResponseMapper initialize() {
        return Mappers.getMapper(TokenToTokenResponseMapper.class);
    }

}
