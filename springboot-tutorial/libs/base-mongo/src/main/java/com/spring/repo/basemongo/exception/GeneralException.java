package com.spring.repo.basemongo.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public abstract class GeneralException extends RuntimeException implements Serializable {
    private String errorCode;
    private int httpStatus = 500;
    private Map<String, Object> params = new HashMap<>();
    protected String[] args = new String[0];

    public GeneralException(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        String msg = String.format("[%s] with http status [%s], args: [%s]", this.getErrorCode(), this.getHttpStatus(),
                Arrays.stream(args).collect(Collectors.joining(",")));
        return msg;
    }
}
