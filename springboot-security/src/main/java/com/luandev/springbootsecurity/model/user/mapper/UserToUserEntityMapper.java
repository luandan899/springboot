package com.luandev.springbootsecurity.model.user.mapper;

import com.luandev.springbootsecurity.model.common.mapper.BaseMapper;
import com.luandev.springbootsecurity.model.user.User;
import com.luandev.springbootsecurity.model.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserToUserEntityMapper extends BaseMapper<User, UserEntity> {

    @Override
    UserEntity map(User source);

    static UserToUserEntityMapper initialize() {
        return Mappers.getMapper(UserToUserEntityMapper.class);
    }

}
