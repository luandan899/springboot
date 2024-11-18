package com.spring.repo.eccormerce.mapper.user;

import com.spring.repo.eccormerce.dao.entity.User;
import com.spring.repo.eccormerce.dto.user.UserDTO;
import com.spring.repo.eccormerce.dto.user.UserPayloadDTO;
import com.spring.repo.eccormerce.enums.UserRole;
import org.mapstruct.*;

@Mapper(
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

    @Mapping(target = "baseId", source = "payloadDTO.userId")
    @Mapping(target = "role", source = "payloadDTO.role", qualifiedByName = "convertRoleUser")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract User generateUser(UserPayloadDTO.UserInfoDTO payloadDTO);

    @Named("convertRoleUser")
    public UserRole convertRoleUser(String role) {
        return UserRole.valueOf(role);
    }

    public abstract UserDTO toDTO(User user);
}
