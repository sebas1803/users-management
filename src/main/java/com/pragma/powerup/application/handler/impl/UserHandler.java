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
import java.util.ArrayList;
import java.util.List;

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
    public void saveAdmin(SaveUserRequestDto saveUserRequestDto) {
        UserModel userModel = userRequestMapper.toUserModel(saveUserRequestDto);
        RoleModel adminRole = new RoleModel();
        adminRole.setName("ROLE_ADMIN");
        List<RoleModel> roles = new ArrayList<>();
        roles.add(adminRole);
        userModel.setRoles(roles);
        userServicePort.saveUser(userModel);
    }

    @Override
    public void saveOwner(SaveUserRequestDto saveUserRequestDto) {
        UserModel userModel = userRequestMapper.toUserModel(saveUserRequestDto);
        RoleModel ownerRole = new RoleModel();
        ownerRole.setName("ROLE_OWNER");
        List<RoleModel> roles = new ArrayList<>();
        roles.add(ownerRole);
        userModel.setRoles(roles);
        userServicePort.saveUser(userModel);
    }

    @Override
    public void saveEmployee(SaveUserRequestDto saveUserRequestDto) {
        UserModel userModel = userRequestMapper.toUserModel(saveUserRequestDto);
        RoleModel employeeRole = new RoleModel();
        employeeRole.setName("ROLE_EMPLOYEE");
        List<RoleModel> roles = new ArrayList<>();
        roles.add(employeeRole);
        userModel.setRoles(roles);
        userServicePort.saveUser(userModel);
    }

    @Override
    public UserResponseDto findById(Long id) {
        UserModel userModel = userServicePort.findById(id);
        return userResponseMapper.toUserResponse(userModel);
    }
}
