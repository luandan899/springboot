package com.luandev.springbootsecurity.model.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private int status;
    private T data;

    public static <T> BaseResponse<T> success() {
        return BaseResponse.<T>builder()
                .status(HttpStatus.OK.value()).build();
    }

    public static <T> BaseResponse<T> success(final T response) {
        return BaseResponse.<T>builder()
                .status(HttpStatus.OK.value())
                .data(response).build();
    }

    public static <T> BaseResponse<T> successOf(final T response) {
        return BaseResponse.<T>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .data(response).build();
    }
}
