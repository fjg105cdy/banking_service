package com.yian.banking_service.services;

import com.yian.banking_service.dtos.UserRequestDTO;
import com.yian.banking_service.dtos.UserResponseDTO;

public interface UserService {
    //결과 형태, 항수이름 (사용자가 입력해야하는 정보)
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

}
