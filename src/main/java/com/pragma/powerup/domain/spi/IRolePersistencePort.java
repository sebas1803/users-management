package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.RoleModel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IRolePersistencePort {
    void saveRole(RoleModel roleModel);

    List<RoleModel> getAllRoles();

    List<RoleModel> getRoleByName(String roleName);

    List<RoleModel> getRoleByNameIn(Collection<String> roleName);
}
