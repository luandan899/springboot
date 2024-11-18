package com.spring.repo.eccormerce.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.repo.common.cqrs.command.CommandBus;
import com.spring.repo.eccormerce.cqrs.user.command.GenerateUserCommand;
import com.spring.repo.eccormerce.dto.user.UserPayloadDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.RetriableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventConsumer {

    @Autowired
    private CommandBus commandBus;

    @Autowired
    private ObjectMapper objectMapper;

    @RetryableTopic(
            attempts = "4", // 3 topic retry + 1 topic DLQ
            backoff = @Backoff(delay = 1000, multiplier = 2),
            autoCreateTopics = "true",
            dltStrategy = DltStrategy.FAIL_ON_ERROR,
            include = {RetriableException.class, RuntimeException.class}
    )
    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.template.default-topic}")
    public void listen(String message) {
        try {
            UserPayloadDTO userDTO = objectMapper.readValue(message, UserPayloadDTO.class);
            switch (userDTO.getAction()) {
                case "send-data":
                    commandBus.dispatch(new GenerateUserCommand(userDTO.getUpdatedData()));

                default:
                    log.error("Invalid action:{}", userDTO.getAction());
            }
            log.info("Received message: {}", userDTO);
        } catch (Exception e) {
            log.error("Error processing message: {}", e.getMessage());
        }
    }

    @DltHandler
    void processDltMessage(@Payload String messsage) {
        log.info("DLT receive message: " + messsage);
    }
}
