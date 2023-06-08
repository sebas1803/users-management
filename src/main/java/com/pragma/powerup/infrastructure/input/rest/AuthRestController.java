package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.infrastructure.security.jwt.request.LoginRequestDto;
import com.pragma.powerup.infrastructure.security.jwt.TokenProvider;
import com.pragma.powerup.infrastructure.security.jwt.response.LoginResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Tag(name = "Auth")
@RequestMapping("api/v1/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthRestController {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Operation(summary = "Authenticate into the application")
    @ApiResponse(responseCode = "200", description = "Successfully authenticated", content = @Content)
    @ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content)
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        LoginResponseDto jwtAuthResponseDTO = new LoginResponseDto(token, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtAuthResponseDTO, HttpStatus.OK);
    }
}
