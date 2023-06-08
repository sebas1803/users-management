package com.pragma.powerup.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleModel {
    private Long id;
    private String name;

    public RoleModel(Long id) {
        this.id = id;
    }

    public RoleModel(String name) {
        this.name = name;
    }
}
