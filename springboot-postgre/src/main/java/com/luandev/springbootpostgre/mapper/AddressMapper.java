package com.luandev.springbootpostgre.mapper;

import com.luandev.springbootpostgre.dto.request.AddressDTO;
import com.luandev.springbootpostgre.model.Address;
import org.mapstruct.*;
import java.util.Set;

@Mapper(
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AddressMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract AddressDTO toDTO(Address entity);

    public abstract Set<AddressDTO> toDTOs(Set<Address> entity);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toEntity(AddressDTO dto, @MappingTarget Address entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Set<Address> toEntities(Set<AddressDTO> dtos);

}
