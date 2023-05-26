package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.SaveUserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.request.IUserRequestMapper;
import com.pragma.powerup.application.mapper.response.IUserResponseMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    @Override
    public void saveUser(SaveUserRequestDto saveUserRequestDto) {
        UserModel userModel = userRequestMapper.toUserModel(saveUserRequestDto);
        userServicePort.saveUser(userModel);
    }

    @Override
    public UserResponseDto findById(Long id) {
        UserModel userModel = userServicePort.findById(id);
        return userResponseMapper.toUserResponse(userModel);
    }
}
