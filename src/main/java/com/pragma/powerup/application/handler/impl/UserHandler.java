package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.SaveUserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.request.IUserRequestMapper;
import com.pragma.powerup.application.mapper.response.IUserResponseMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

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

    private void saveUserWithRole(SaveUserRequestDto saveUserRequestDto, String roleName) {
        UserModel userModel = userRequestMapper.toUserModel(saveUserRequestDto);
        RoleModel roleModel = new RoleModel();
        roleModel.setName(roleName);
        userModel.setRoles(Collections.singletonList(roleModel));
        userServicePort.saveUser(userModel);
    }

    @Override
    public void saveAdmin(SaveUserRequestDto saveUserRequestDto) {
        saveUserWithRole(saveUserRequestDto, "ROLE_ADMIN");
    }

    @Override
    public void saveOwner(SaveUserRequestDto saveUserRequestDto) {
        saveUserWithRole(saveUserRequestDto, "ROLE_OWNER");
    }

    @Override
    public void saveEmployee(SaveUserRequestDto saveUserRequestDto) {
        saveUserWithRole(saveUserRequestDto, "ROLE_EMPLOYEE");
    }

    @Override
    public void saveClient(SaveUserRequestDto saveUserRequestDto) {
        saveUserWithRole(saveUserRequestDto, "ROLE_CLIENT");
    }

    @Override
    public UserResponseDto findById(Long id) {
        UserModel userModel = userServicePort.findById(id);
        return userResponseMapper.toUserResponse(userModel);
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        UserModel userModel = userServicePort.findByEmail(email);
        return userResponseMapper.toUserResponse(userModel);
    }
}
