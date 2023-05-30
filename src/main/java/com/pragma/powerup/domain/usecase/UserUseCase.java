package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;

    @Override
    public void saveUser(UserModel userModel) {
        if (userModel.getRoles() == null || userModel.getRoles().isEmpty()) {
            throw new IllegalArgumentException("User must have at least one role");
        }

        List<String> roleNames = userModel.getRoles().stream()
                .map(RoleModel::getName)
                .collect(Collectors.toList());
        List<RoleModel> foundRoles = rolePersistencePort.getRoleByNameIn(roleNames);
        if (foundRoles.size() < roleNames.size()) {
            throw new DomainException("Some roles are invalid or not found");
        }
        List<RoleModel> roles = new ArrayList<>(foundRoles);

        userModel.setRoles(roles);
        userPersistencePort.saveUser(userModel);
    }

    @Override
    public UserModel findById(Long id) {
        return userPersistencePort.findById(id);
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return userPersistencePort.findByEmail(email);
    }

}
