package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.SaveUserRequestDto;
import com.pragma.powerup.application.handler.impl.UserHandler;
import com.pragma.powerup.application.mapper.request.IUserRequestMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.UserModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserHandlerTest {
    @Spy
    private IUserServicePort userServicePort;

    @Spy
    private IUserRequestMapper userRequestMapper;

    @InjectMocks
    private UserHandler userHandler;

    @Test
    public void testSaveUser() {

        // Given
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_OWNER");
        SaveUserRequestDto saveUserRequestDto = new SaveUserRequestDto();
        saveUserRequestDto.setPhone("+123456782011");
        saveUserRequestDto.setIdDocument("1234161");
        saveUserRequestDto.setEmail("sebastian@gmail.com");
        saveUserRequestDto.setName("sebastian");
        saveUserRequestDto.setLastName("alfaro");
        saveUserRequestDto.setPassword("1234");
        saveUserRequestDto.setRoles(roles);

        UserModel userModel = new UserModel();
        when(userRequestMapper.toUserModel(saveUserRequestDto)).thenReturn(userModel);

        // When
        userHandler.saveUser(saveUserRequestDto);

        // Then
        verify(userServicePort).saveUser(userModel);
    }
}
