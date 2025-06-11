package com.yian.banking_service.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailRequestDTO {
    @NotBlank(message="Email must not be empty")
    @Email(message="Email is not valid")
    private String email;

}
