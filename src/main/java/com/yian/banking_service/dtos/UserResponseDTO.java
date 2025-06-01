package com.yian.banking_service.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserResponseDTO {
    private String id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
}
