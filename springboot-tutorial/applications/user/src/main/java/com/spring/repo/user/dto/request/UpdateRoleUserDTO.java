package com.spring.repo.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateRoleUserDTO {
    private String id;
    private String role;
}
