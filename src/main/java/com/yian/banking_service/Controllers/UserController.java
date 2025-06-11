package com.yian.banking_service.Controllers;

import com.yian.banking_service.dtos.ApiResponseDTO;
import com.yian.banking_service.dtos.EmailRequestDTO;
import com.yian.banking_service.dtos.UserRequestDTO;
import com.yian.banking_service.dtos.UserResponseDTO;
import com.yian.banking_service.entities.User;
import com.yian.banking_service.services.EmailService;
import com.yian.banking_service.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final EmailService emailService;

    //등록하는 API
    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> createUser(
            @Valid @RequestBody UserRequestDTO userRequestDTO
    ) {
        UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);
        //welcome email 보내는 logic
        emailService.sendEmail(userResponseDTO.getEmail(),userRequestDTO.getUsername());

        //response 표준화
        ApiResponseDTO<UserResponseDTO> response = ApiResponseDTO.<UserResponseDTO>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("User created successfully")
                .data(userResponseDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //전체 유저 가져오기 API
    @GetMapping("/all")
    public ResponseEntity<ApiResponseDTO<List<UserResponseDTO>>> getAllUsers() {
        List<UserResponseDTO> users = userService.findAllUsers();
        ApiResponseDTO<List<UserResponseDTO>> response = ApiResponseDTO.<List<UserResponseDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfull get user info")
                .data(users)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> getUserById(@PathVariable String userId){
        UserResponseDTO user = userService.findUserById(userId);
        ApiResponseDTO<UserResponseDTO> response = ApiResponseDTO.<UserResponseDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("user's information successfully found")
                .data(user)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //이메일 인증
    @PostMapping("/send/email")
    public ResponseEntity<ApiResponseDTO<Boolean>> sendEmail(
            @RequestBody @Valid EmailRequestDTO emailRequestDTO
    ){
        userService.sendEmailVerification(emailRequestDTO);

        ApiResponseDTO<Boolean> response = ApiResponseDTO.<Boolean>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successful send email")
                .data(true)
                .build();

        return ResponseEntity.ok(response);

    }

}
