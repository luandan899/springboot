package com.spring.repo.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaSenderService {
    private static final Logger LOG = LoggerFactory
            .getLogger(KafkaSenderService.class);

    @Autowired
    KafkaTemplate<String, Object> template;

    public void send(String topic, Object payload) {
        LOG.info("sending payload='{}' to topic='{}'", payload, topic);
        CompletableFuture<SendResult<String, Object>> result =
                template.send(topic, payload);
        result.whenComplete((sr, ex) -> {
            if (ex != null) {
                LOG.error("Unable to send message=[{}] due to : {}", payload,
                        ex.getMessage());
                return;
            }
            LOG.info(" Kafka send: {} success",
                    sr.getProducerRecord().value());
        });
    }
}
