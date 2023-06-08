package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.SaveRoleRequestDto;
import com.pragma.powerup.application.dto.response.RoleResponseDto;
import com.pragma.powerup.application.handler.IRoleHandler;
import com.pragma.powerup.application.mapper.request.IRoleRequestMapper;
import com.pragma.powerup.application.mapper.response.IRoleResponseMapper;
import com.pragma.powerup.domain.api.IRoleServicePort;
import com.pragma.powerup.domain.model.RoleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleHandler implements IRoleHandler {
    private final IRoleServicePort roleServicePort;
    private final IRoleRequestMapper roleRequestMapper;
    private final IRoleResponseMapper roleResponseMapper;

    @Override
    public void saveRole(SaveRoleRequestDto roleRequestDto) {
        RoleModel roleModel = roleRequestMapper.toRoleModel(roleRequestDto);
        roleServicePort.saveRole(roleModel);
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {
        List<RoleModel> roleModels = roleServicePort.getAllRoles();
        return roleResponseMapper.toResponseList(roleModels);
    }
}
