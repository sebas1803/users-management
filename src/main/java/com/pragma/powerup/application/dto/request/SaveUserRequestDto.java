package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@RequiredArgsConstructor
public class SaveUserRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Lastname is required")
    private String lastName;

    @NotBlank(message = "Identity document is required")
    private String idDocument;

    @NotBlank(message = "Phone number is required")
    @Size(max = 13, message = "Phone number must have a maximum of 13 characters")
    @Pattern(regexp = "\\+?[0-9]+", message = "Phone number must contain only digits and can have '+' symbol")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must have a valid format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}