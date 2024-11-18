package com.luandev.springbootpostgre.common.exception.handler;

import com.luandev.springbootpostgre.common.enums.ErrorMessage;
import com.luandev.springbootpostgre.common.exception.BusinessLogicException;
import com.luandev.springbootpostgre.common.response.BaseResponse;
import com.luandev.springbootpostgre.common.response.ErrorBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessLogicException.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Bad Request",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "404 Response",
                                    summary = "Handle Business Logic Exception"
                            ))})
    })
    protected ResponseEntity<Object> handleException(final BusinessLogicException ex) {

        ErrorMessage errorMessage = ex.getErrorMessage();
        BaseResponse baseResponse = BaseResponse.builder().status(HttpStatus.BAD_REQUEST.value())
                .data(
                        ErrorBody.builder()
                                .code(errorMessage.getCode())
                                .message(errorMessage.getMessageFm().formatted(ex.getParams())).build()).build();
        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }


}
