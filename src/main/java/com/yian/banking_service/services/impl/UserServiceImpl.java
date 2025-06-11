package com.yian.banking_service.services.impl;

import com.yian.banking_service.Mappers.UserMapper;
import com.yian.banking_service.dtos.EmailRequestDTO;
import com.yian.banking_service.dtos.UserRequestDTO;
import com.yian.banking_service.dtos.UserResponseDTO;
import com.yian.banking_service.entities.User;
import com.yian.banking_service.exception.DuplicateResourceException;
import com.yian.banking_service.exception.ResourceNotFoundException;
import com.yian.banking_service.repositories.UserRepository;
import com.yian.banking_service.services.EmailService;
import com.yian.banking_service.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EmailService emailService;
    private final RedisTemplate<String, String> redisTemplate;


    @Override
    public List<UserResponseDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::mapToUserResponseDTO)
                .collect(Collectors.toList());

    }

    @Override
    public UserResponseDTO findUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        return userMapper.mapToUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        //이메일 유무 체크
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
//            throw new IllegalArgumentException("Email is already in use");
            throw new DuplicateResourceException("User","email",userRequestDTO.getEmail());
        }
        //Convert DTO-> Entity
        User user= userMapper.mapToUserEntity(userRequestDTO);

        //save entity
        User savedUser = userRepository.save(user);

        //convert Entity -> DTO
        return userMapper.mapToUserResponseDTO(savedUser);
    }

    @Override
    public String sendEmailVerification(EmailRequestDTO emailRequestDTO) {
        //6자리로 된 랜덤번호 생성-> 랜덤번호 저장 -> 이메일로 보내기

        String code = String.format("%06d",new Random().nextInt(1000000));
        emailService.sendEmailVerificationCode(emailRequestDTO.getEmail(), code);

        //저장하는 로직 추가(임시적으로 저장하는 디비 만들것(Redis))
        redisTemplate.opsForValue().set("email:verify:" + emailRequestDTO.getEmail(), code);

        String stored = redisTemplate.opsForValue().get("email:verify:" + emailRequestDTO.getEmail());
        log.info("Redis에서 방금 저장된 값: {}", stored);





        return code;


    }
}
