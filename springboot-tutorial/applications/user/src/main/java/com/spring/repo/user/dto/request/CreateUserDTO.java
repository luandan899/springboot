package com.spring.repo.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class CreateUserDTO {
    private String name;
    private String phone;
    private String email;
    private String password;
}
