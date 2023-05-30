package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findRoleByName(String roleName);

    List<RoleEntity> findRoleByNameIn(Collection<String> roleName);
}
