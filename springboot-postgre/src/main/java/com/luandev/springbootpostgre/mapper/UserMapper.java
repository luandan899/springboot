package com.luandev.springbootpostgre.mapper;

import com.luandev.springbootpostgre.dto.request.AddressDTO;
import com.luandev.springbootpostgre.dto.request.UserRequestDTO;
import com.luandev.springbootpostgre.dto.response.BaseUserResponseDTO;
import com.luandev.springbootpostgre.dto.response.DetailUserResponseDTO;
import com.luandev.springbootpostgre.enums.UserType;
import com.luandev.springbootpostgre.model.Address;
import com.luandev.springbootpostgre.model.User;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Slf4j
public abstract class UserMapper {
    @Autowired
    private AddressMapper addressMapper;

    public abstract BaseUserResponseDTO toDTO(User entity);

    @Mapping(target = "addresses", source = "addresses", qualifiedByName = "convertAddressDTO")
    public abstract DetailUserResponseDTO toUserDetailResponseDTO(User entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "addresses", source = "addresses", qualifiedByName = "convertAddress")
    @Mapping(target = "type", source = "type", qualifiedByName = "convertType")
    public abstract User toEntity(UserRequestDTO dto);


    @Named("convertAddress")
    public Set<Address> convertAddress(Set<AddressDTO> addresses) {
        if (addresses.isEmpty()) {
            return new HashSet<>();
        }
        return addressMapper.toEntities(addresses);
    }

    @Named("convertAddressDTO")
    public Set<AddressDTO> convertAddressDTO(Set<Address> addresses) {
        if (addresses.isEmpty()) {
            return new HashSet<>();
        }
        return addressMapper.toDTOs(addresses);
    }

    @Named("convertType")
    public UserType convertType(String type) {
        return UserType.valueOf(type.toUpperCase());
    }

}
