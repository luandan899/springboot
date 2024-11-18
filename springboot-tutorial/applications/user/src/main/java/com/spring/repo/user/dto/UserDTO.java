package com.spring.repo.user.dto;

import com.spring.repo.basemongo.core.dto.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UserDTO extends BaseDTO {
    private String name;
    private String phone;
    private String email;
}
