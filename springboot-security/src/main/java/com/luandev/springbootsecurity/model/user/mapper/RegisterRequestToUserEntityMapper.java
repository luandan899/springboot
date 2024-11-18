package com.luandev.springbootsecurity.model.user.mapper;

import com.luandev.springbootsecurity.model.common.mapper.BaseMapper;
import com.luandev.springbootsecurity.model.user.dto.request.RegisterRequest;
import com.luandev.springbootsecurity.model.user.entity.UserEntity;
import com.luandev.springbootsecurity.model.user.enums.UserType;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterRequestToUserEntityMapper extends BaseMapper<RegisterRequest, UserEntity> {
    
    @Named("mapForSaving")
    default UserEntity mapForSaving(RegisterRequest userRegisterRequest) {

        UserType userType = "admin".equalsIgnoreCase(userRegisterRequest.getRole()) ? UserType.ADMIN : UserType.USER;

        return UserEntity.builder().email(userRegisterRequest.getEmail()).firstName(userRegisterRequest.getFirstName()).lastName(userRegisterRequest.getLastName()).phoneNumber(userRegisterRequest.getPhoneNumber()).userType(userType).build();
    }

    static RegisterRequestToUserEntityMapper initialize() {
        return Mappers.getMapper(RegisterRequestToUserEntityMapper.class);
    }

}