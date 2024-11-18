package com.spring.repo.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class KafkaBaseMessage<T> {
    private String action;
    private T updatedData;

    @Override
    public String toString() {
        return "KafkaBaseMessage{" +
                "action='" + action + '\'' +
                ", updatedData=" + updatedData +
                '}';
    }
}
