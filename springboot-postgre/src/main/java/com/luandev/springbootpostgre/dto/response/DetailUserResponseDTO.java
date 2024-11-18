package com.luandev.springbootpostgre.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.luandev.springbootpostgre.dto.request.AddressDTO;
import com.luandev.springbootpostgre.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailUserResponseDTO extends BaseUserResponseDTO {
    private String phone;
    private Date dateOfBirth;
    private Gender gender;
    private String username;
    private String type;
    private Set<AddressDTO> addresses;

}
