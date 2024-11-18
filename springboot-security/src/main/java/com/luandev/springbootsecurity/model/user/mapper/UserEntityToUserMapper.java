package com.luandev.springbootsecurity.model.user.mapper;

import com.luandev.springbootsecurity.model.common.mapper.BaseMapper;
import com.luandev.springbootsecurity.model.user.User;
import com.luandev.springbootsecurity.model.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserEntityToUserMapper extends BaseMapper<UserEntity, User> {

    @Override
    User map(UserEntity source);

    static UserEntityToUserMapper initialize() {
        return Mappers.getMapper(UserEntityToUserMapper.class);
    }

}
