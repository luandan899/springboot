package com.spring.repo.user.kafka.dto.source;

import com.spring.repo.kafka.dto.KafkaBaseMessage;
import com.spring.repo.user.kafka.dto.payload.SendUserPayload;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class SendUserSource extends KafkaBaseMessage<SendUserPayload> {
}
