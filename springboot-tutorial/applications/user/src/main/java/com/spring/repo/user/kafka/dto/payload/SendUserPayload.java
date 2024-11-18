package com.spring.repo.user.kafka.dto.payload;

import com.spring.repo.user.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendUserPayload extends BasePayload {
    private String name;
    private String email;
    private String password;
    private UserRole role;
}
