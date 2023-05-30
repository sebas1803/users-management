package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
public class SaveRoleRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    public SaveRoleRequestDto(String name) {
        this.name = name;
    }
}
