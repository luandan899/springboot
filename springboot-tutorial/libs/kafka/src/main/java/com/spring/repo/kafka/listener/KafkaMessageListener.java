package com.spring.repo.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.kafka.support.Acknowledgment;

public interface KafkaMessageListener {
    Void handleMessage(JsonNode event);

    void onMessage(String rawMsg, Acknowledgment acknowledgment) throws JsonProcessingException;

    String getMessageActionName();
}
