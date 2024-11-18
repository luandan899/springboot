package com.spring.repo.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UpdateSocialUserDTO {
    private String id;
    private String socialName;
    private String account;
    private String password;
}
