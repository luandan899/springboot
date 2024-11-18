package com.spring.repo.user.service.impl;

import com.spring.repo.user.enums.ExternalTriggerType;
import com.spring.repo.user.kafka.dto.event.SendUserEvent;
import com.spring.repo.user.kafka.dto.payload.BasePayload;
import com.spring.repo.user.kafka.dto.payload.SendUserPayload;
import com.spring.repo.user.kafka.dto.source.SendUserSource;
import com.spring.repo.user.service.ExternalSystemTriggerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ExternalSystemTriggerServiceImpl implements ExternalSystemTriggerService {
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void trigger(ExternalTriggerType trigger, BasePayload payload) {
        if (trigger.equals(ExternalTriggerType.SEND_DATA_USER)) {
            eventPublisher.publishEvent(triggerSendUser(payload));
        }
    }

    private ApplicationEvent triggerSendUser(BasePayload payload) {
        log.info("triggerSendUser");
        return new SendUserEvent(SendUserSource.builder().updatedData((SendUserPayload) payload).build());
    }

}
