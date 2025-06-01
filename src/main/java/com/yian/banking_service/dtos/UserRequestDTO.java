package com.yian.banking_service.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserRequestDTO {
    @NotBlank(message="Username is required")
    private String username;

    @NotBlank(message="password is required")
    @Size(min=8, max=16, message="password must be at least 8 characters and at most 16 characters ")
    private String password;

    @NotBlank(message="Email is required")
    @Email(message="Email is invalid")
    private String email;

}
