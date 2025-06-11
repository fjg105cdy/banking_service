package com.yian.banking_service.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EmailVerifyRequestDTO {
    @NotBlank(message="Email must not be empty")
    @Email(message="Email is not valid")
    private String email;

    @NotBlank(message="Code must not be empty")
    @Size(min=6,max=6,message="Code must be exactly 6 digits")
    private String code;
}
