package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRoleServicePort;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoleUseCase implements IRoleServicePort {
    private final IRolePersistencePort rolePersistencePort;
    @Override
    public void saveRole(RoleModel roleModel) {
        rolePersistencePort.saveRole(roleModel);
    }

    @Override
    public List<RoleModel> getAllRoles() {
        return rolePersistencePort.getAllRoles();
    }
}