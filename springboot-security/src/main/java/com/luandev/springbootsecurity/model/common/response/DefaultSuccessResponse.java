package com.luandev.springbootsecurity.model.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DefaultSuccessResponse {
    private String message;

    public static DefaultSuccessResponse defaultResponse() {
        return DefaultSuccessResponse
                .builder()
                .message("Data updated successfully!")
                .build();
    }

    public static DefaultSuccessResponse defaultResponseDeleted() {
        return DefaultSuccessResponse
                .builder()
                .message("Data deleted successfully!")
                .build();
    }
}