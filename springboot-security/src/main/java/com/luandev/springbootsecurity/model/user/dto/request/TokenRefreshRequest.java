package com.luandev.springbootsecurity.model.user.dto.request;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenRefreshRequest {

    @NotBlank
    private String token;

}
