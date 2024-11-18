package com.spring.repo.common.response.builder;

import com.spring.repo.common.response.BaseResponse;
import com.spring.repo.common.response.BaseResponseError;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
public class BaseResponseBuilder {

    private int status;


    public static BaseResponseBuilder ok() {
        return status(HttpStatus.OK);
    }


    public static BaseResponseBuilder badRequest() {
        return status(HttpStatus.BAD_REQUEST);
    }


    public <T> BaseResponse<T> body(T response) {
        return this.buildEntityResponse(response);
    }


    public <T extends BaseResponseError.Error> BaseResponseError body(BaseResponseError.Error error) {
        return this.okWithError(error);
    }

    private static BaseResponseBuilder status(HttpStatus status) {
        return new BaseResponseBuilder(status.value());
    }

    private <T> BaseResponse<T> buildEntityResponse(T response) {
        return BaseResponse
                .<T>builder()
                .status(this.status)
                .data(response)
                .build();
    }

    private BaseResponseError okWithError(BaseResponseError.Error error) {
        return BaseResponseError
                .builder()
                .status(this.status)
                .data(error)
                .build();
    }

}
