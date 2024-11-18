package com.luandev.springbootpostgre.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luandev.springbootpostgre.enums.Gender;
import com.luandev.springbootpostgre.enums.UserStatus;
import com.luandev.springbootpostgre.util.PhoneNumber;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotNull(message = "name must be not null")
    private String name;

    @Email(message = "email invalid format")
    private String email;

    @PhoneNumber(message = "phone invalid format")
    private String phone;

    @NotNull(message = "dateOfBirth must be not null")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date dateOfBirth;

    private Gender gender;

    @NotNull(message = "username must be not null")
    private String username;

    @NotNull(message = "password must be not null")
    private String password;

    @NotNull(message = "type must be not null")
    private String type;

    private UserStatus status;

    @NotEmpty(message = "addresses can not empty")
    private Set<AddressDTO> addresses;

}
