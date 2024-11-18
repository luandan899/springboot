package com.spring.repo.kafka.dto;


public class KafkaMessageConstant {
    static String FIELD_NAME_ACTION = "action";
    static String FIELD_NAME_UPDATED_DATA = "updatedData";

    public static String getAction() {
        return FIELD_NAME_ACTION;
    }

    public static String getUpdatedData() {
        return FIELD_NAME_UPDATED_DATA;
    }
}
