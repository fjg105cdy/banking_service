package com.yian.banking_service.services.impl;

import com.yian.banking_service.Mappers.UserMapper;
import com.yian.banking_service.dtos.UserRequestDTO;
import com.yian.banking_service.dtos.UserResponseDTO;
import com.yian.banking_service.entities.User;
import com.yian.banking_service.repositories.UserRepository;
import com.yian.banking_service.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        //이메일 유무 체크
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new IllegalArgumentException("Email is already in use");

        }
        //Convert DTO-> Entity
        User user= userMapper.mapToUserEntity(userRequestDTO);

        //save entity
        User savedUser = userRepository.save(user);

        //convert Entity -> DTO
        return userMapper.mapToUserResponseDTO(savedUser);
    }
}
