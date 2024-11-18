package com.luandev.springbootsecurity.secutiry;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.luandev.springbootsecurity.model.common.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }

    @Override
    public void commence(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse,
                         final AuthenticationException authenticationException) throws IOException {

        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

        final BaseResponse baseResponse = BaseResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value()).build();

        final String responseBody = OBJECT_MAPPER
                .writer(DateFormat.getDateInstance())
                .writeValueAsString(baseResponse);

        httpServletResponse.getOutputStream()
                .write(responseBody.getBytes());

    }

}
