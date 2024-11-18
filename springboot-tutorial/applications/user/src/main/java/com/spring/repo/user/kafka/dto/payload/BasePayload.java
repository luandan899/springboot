package com.spring.repo.user.kafka.dto.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BasePayload {

    private String userId;
}
