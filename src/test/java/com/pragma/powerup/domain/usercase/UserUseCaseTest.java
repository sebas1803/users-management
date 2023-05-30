package com.pragma.powerup.domain.usercase;

import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.usecase.UserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserUseCaseTest {

    @Spy
    private IUserPersistencePort userPersistencePort;

    @Spy
    private IRolePersistencePort rolePersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    public void setup() {
        userUseCase = new UserUseCase(userPersistencePort, rolePersistencePort);
    }

    @Test
    public void testSaveUserWithRoles() {
        // Given
        List<RoleModel> roles = new ArrayList<>();
        roles.add(new RoleModel("ROLE_OWNER"));
        UserModel userModel = new UserModel();
        userModel.setIdDocument("123456789");
        userModel.setName("John");
        userModel.setLastName("Doe");
        userModel.setEmail("johndoe@gmail.com");
        userModel.setPassword("password");
        userModel.setPhone("555-5555");
        userModel.setRoles(roles);

        // Mocking rolePersistencePort
        RoleModel ownerRole = new RoleModel("ROLE_OWNER");
        when(rolePersistencePort.getRoleByNameIn(anyList())).thenReturn(List.of(ownerRole));

        // When
        userUseCase.saveUser(userModel);

        // Then
        verify(rolePersistencePort).getRoleByNameIn(roles.stream().map(RoleModel::getName).collect(Collectors.toList()));
        verify(userPersistencePort).saveUser(userModel);
    }

    @Test
    public void testSaveUserWithoutRoles() {
        // Given
        UserModel userModel = new UserModel();
        userModel.setIdDocument("123456789");
        userModel.setName("John");
        userModel.setLastName("Doe");
        userModel.setEmail("johndoe@gmail.com");
        userModel.setPassword("password");
        userModel.setPhone("555-5555");

        // when & then
        assertThrows(IllegalArgumentException.class, () -> userUseCase.saveUser(userModel));
    }
}
