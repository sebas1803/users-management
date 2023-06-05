package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.domain.model.RoleModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class UserResponseDto {
    private String name;
    private String lastName;
    private String idDocument;
    private String phone;
    private String email;
    private List<String> roles;
}
