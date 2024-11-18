package com.spring.repo.user.mapper;

import com.spring.repo.user.documents.User;
import com.spring.repo.user.dto.BaseResponseDTO;
import com.spring.repo.user.dto.UserDTO;
import com.spring.repo.user.dto.request.CreateUserDTO;
import com.spring.repo.user.dto.request.UpdateUserDTO;
import com.spring.repo.user.kafka.dto.payload.SendUserPayload;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserDTO toDTO(User entity);

    BaseResponseDTO toBaseDTO(User entity);

    void create(CreateUserDTO dto, @MappingTarget User entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserById(UpdateUserDTO dto, @MappingTarget User entity);

    @Mapping(target = "userId", source = "id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SendUserPayload triggerSendUser(User entity);

    List<UserDTO> toDTOs(List<User> entities);

}
