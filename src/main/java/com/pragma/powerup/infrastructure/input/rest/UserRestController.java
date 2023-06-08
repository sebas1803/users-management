package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.SaveUserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Users")
@RequestMapping("api/v1/users")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Create a new admin")
    @ApiResponse(responseCode = "201", description = "Admin created", content = @Content)
    @PostMapping("/admin")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody SaveUserRequestDto saveUserRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        userHandler.saveAdmin(saveUserRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Admin user created successfully");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Create a new owner")
    @ApiResponse(responseCode = "201", description = "Owner created", content = @Content)
    @PostMapping("/owner")
    public ResponseEntity<?> createOwner(@Valid @RequestBody SaveUserRequestDto saveUserRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        userHandler.saveOwner(saveUserRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Owner user created successfully");
    }

    @PreAuthorize("hasAuthority('ROLE_OWNER')")
    @Operation(summary = "Create a new employee")
    @ApiResponse(responseCode = "201", description = "Employee created", content = @Content)
    @PostMapping("/employee")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody SaveUserRequestDto saveUserRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        userHandler.saveEmployee(saveUserRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee user created successfully");
    }

    @Operation(summary = "Create a new client")
    @ApiResponse(responseCode = "201", description = "Client created", content = @Content)
    @PostMapping("/client")
    public ResponseEntity<?> createClient(@Valid @RequestBody SaveUserRequestDto saveUserRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        userHandler.saveClient(saveUserRequestDto);
        return ResponseEntity.ok("Client user created successfully");
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE')")
    @Operation(summary = "Returns user by ID")
    @ApiResponse(responseCode = "200", description = "User found", content = @Content)
    @GetMapping("/findById/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        UserResponseDto userResponse = userHandler.findById(userId);
        return ResponseEntity.ok(userResponse);
    }

    @Operation(summary = "Returns user by email")
    @ApiResponse(responseCode = "200", description = "User found", content = @Content)
    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        UserResponseDto userResponse = userHandler.findByEmail(email);
        return ResponseEntity.ok(userResponse);
    }
}
