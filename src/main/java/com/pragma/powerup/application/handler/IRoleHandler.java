package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.SaveRoleRequestDto;
import com.pragma.powerup.application.dto.response.RoleResponseDto;

import java.util.List;

public interface IRoleHandler {
    void saveRole(SaveRoleRequestDto roleRequestDto);

    List<RoleResponseDto> getAllRoles();
}
