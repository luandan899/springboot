package com.luandev.springbootsecurity.model.common.response;

import lombok.Builder;

@Builder
public class ErrorBody {
    private String code;
    private String message;
}
