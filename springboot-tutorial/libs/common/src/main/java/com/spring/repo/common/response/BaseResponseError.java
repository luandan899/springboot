package com.spring.repo.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Builder
public class BaseResponseError<T extends BaseResponseError.Error> {


    private int status;
    private T data;


    @Getter
    @Setter
    @Builder
    public static class Error {
        private String code;
        private String message;
    }
}
