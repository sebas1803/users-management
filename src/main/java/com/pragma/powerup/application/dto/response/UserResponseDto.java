package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserResponseDto {
    private String name;
    private String lastName;
    private String idDocument;
    private String phone;
    private String email;
    private String password;
}
