package com.spring.repo.eccormerce.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserPayloadDTO {

    private String action;

    @JsonProperty("updatedData")
    private UserInfoDTO updatedData;


    @Getter
    @AllArgsConstructor
    public static class UserInfoDTO {
        private String userId;
        private String name;
        private String email;
        private String password;
        private String role;
    }
}
