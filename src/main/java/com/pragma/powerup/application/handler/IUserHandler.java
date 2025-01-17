package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.SaveUserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;

public interface IUserHandler {
    void saveUser(SaveUserRequestDto saveUserRequestDto);

    void saveAdmin(SaveUserRequestDto saveUserRequestDto);

    void saveOwner(SaveUserRequestDto saveUserRequestDto);

    void saveEmployee(SaveUserRequestDto saveUserRequestDto);

    void saveClient(SaveUserRequestDto saveUserRequestDto);

    UserResponseDto findById(Long id);

    UserResponseDto findByEmail(String email);
}
