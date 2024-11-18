package com.luandev.springbootsecurity.model.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @Email(message = "Please enter valid e-mail address")
    private String email;

    private String password;

    @NotBlank(message = "First name can't be blank.")
    private String firstName;

    @NotBlank(message = "Last name can't be blank.")
    private String lastName;

    @NotBlank(message = "Phone number can't be blank.")
    private String phoneNumber;

    @NotBlank(message = "Role can't be blank.")
    private String role;

}
