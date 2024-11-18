package com.spring.repo.user.kafka;

import com.spring.repo.kafka.service.KafkaSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaTriggerEventHandler<T extends BaseKafkaEventWrapper> implements ApplicationListener<T> {

    @Value("${spring.kafka.template.default-topic}")
    private String defaultTopic;
    @Autowired
    private KafkaSenderService kafkaSenderService;

    @Override
    public void onApplicationEvent(BaseKafkaEventWrapper event) {
        this.kafkaSenderService.send(defaultTopic, event.getSource());
    }


}
