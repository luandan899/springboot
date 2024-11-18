package com.luandev.springbootsecurity.model.common.exception.handler;

import com.luandev.springbootsecurity.model.common.exception.BusinessLogicException;
import com.luandev.springbootsecurity.model.common.response.BaseResponse;
import com.luandev.springbootsecurity.model.common.response.ErrorBody;
import com.luandev.springbootsecurity.model.user.enums.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessLogicException.class)
    protected ResponseEntity<Object> handleException(final BusinessLogicException ex) {

        ErrorMessage errorMessage = ex.getErrorMessage();
        if (ex.getParams() != null) {
            errorMessage.getMessageFm().formatted(ex.getParams());
        }

        BaseResponse baseResponse = BaseResponse.builder().status(HttpStatus.BAD_REQUEST.value())
                .data(
                        ErrorBody.builder()
                                .code(errorMessage.getCode())
                                .message(errorMessage.getMessageFm()).build()).build();
        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }


}
