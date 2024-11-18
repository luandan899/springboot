package com.spring.repo.user.kafka.dto.event;

import com.spring.repo.user.kafka.BaseKafkaEventWrapper;
import com.spring.repo.user.kafka.dto.source.SendUserSource;

public class SendUserEvent extends BaseKafkaEventWrapper<SendUserSource> {
    public SendUserEvent(SendUserSource source) {
        super(source);
    }

    @Override
    public String action() {
        return "send-data";
    }
}
