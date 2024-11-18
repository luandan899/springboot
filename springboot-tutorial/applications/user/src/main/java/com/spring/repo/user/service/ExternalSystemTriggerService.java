package com.spring.repo.user.service;


import com.spring.repo.user.enums.ExternalTriggerType;
import com.spring.repo.user.kafka.dto.payload.BasePayload;

public interface ExternalSystemTriggerService {

    void trigger(ExternalTriggerType trigger, BasePayload payload);
}
