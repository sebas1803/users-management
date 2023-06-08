package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public void saveRole(RoleModel roleModel) {
        RoleEntity roleEntity = roleEntityMapper.toEntity(roleModel);
        roleRepository.save(roleEntity);
    }

    @Override
    public List<RoleModel> getAllRoles() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        return roleEntityMapper.toRoleModelList(roleEntities);
    }

    @Override
    public List<RoleModel> getRoleByName(String roleName) {
        List<RoleEntity> entityList = roleRepository.findRoleByName(roleName);
        return new ArrayList<>(roleEntityMapper.toRoleModelList(entityList));
    }

    @Override
    public List<RoleModel> getRoleByNameIn(Collection<String> roleName) {
        List<RoleEntity> roleEntities = roleRepository.findRoleByNameIn(roleName);
        return roleEntityMapper.toRoleModelList(roleEntities);
    }
}
