package com.spring.repo.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.repo.kafka.dto.KafkaMessageConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.Acknowledgment;

import java.util.function.Function;

@Slf4j
public class KafkaUtils {

    public static void commonDolKafkaOnMessage(
            String rawMsg,
            Acknowledgment acknowledgment,
            String messageActionName,
            Function<JsonNode, Void> function)
            throws JsonProcessingException {
        acknowledgment.acknowledge();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode event = objectMapper.readValue(rawMsg, JsonNode.class);
        String eventType = event.get(KafkaMessageConstant.getAction()).asText();
        log.info("Kafka message received: " + eventType);
        if (eventType.equals(messageActionName)) {
            function.apply(event);
        }
    }
}
