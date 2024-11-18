package com.luandev.springbootsecurity.model.user;

import com.luandev.springbootsecurity.model.user.enums.UserStatus;
import com.luandev.springbootsecurity.model.user.enums.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class User {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UserStatus userStatus;
    private UserType userType;
}
