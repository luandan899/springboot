package com.spring.repo.user.kafka;

import com.spring.repo.kafka.dto.KafkaBaseMessage;
import org.springframework.context.ApplicationEvent;

public abstract class BaseKafkaEventWrapper<T extends KafkaBaseMessage> extends ApplicationEvent {

    public BaseKafkaEventWrapper(T source) {
        super(source);
        source.setAction(this.action());
    }

    public abstract String action();


}
