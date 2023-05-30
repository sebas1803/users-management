package com.pragma.powerup.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.powerup.application.dto.request.SaveUserRequestDto;
import com.pragma.powerup.application.handler.IUserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserRestControllerTest {

    @Mock
    private IUserHandler userHandler;

    @InjectMocks
    private UserRestController userRestController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
    }

    @Test
    void testCreateUser() throws Exception {
        // Given
        SaveUserRequestDto requestDto = new SaveUserRequestDto();
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_OWNER");
        requestDto.setIdDocument("1234161");
        requestDto.setName("John");
        requestDto.setLastName("Doe");
        requestDto.setEmail("johndoe@gmail.com");
        requestDto.setPassword("password");
        requestDto.setPhone("+123456782011");
        requestDto.setRoles(roles);

        // When
        mockMvc.perform(post("/api/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(requestDto)))
                .andExpect(status().isCreated());

        // Then
        verify(userHandler, times(1)).saveUser(any(SaveUserRequestDto.class));
    }
}
