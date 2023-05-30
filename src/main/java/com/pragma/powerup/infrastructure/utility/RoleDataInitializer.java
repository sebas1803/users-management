package com.pragma.powerup.infrastructure.utility;

import com.pragma.powerup.application.dto.request.SaveRoleRequestDto;
import com.pragma.powerup.application.handler.impl.RoleHandler;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class RoleDataInitializer implements ApplicationRunner {
    private final RoleHandler roleHandler;

    @Override
    public void run(ApplicationArguments args) {
        if (roleHandler.getAllRoles().isEmpty()) {
            List<SaveRoleRequestDto> defaultRoles = getDefaultRoles();
            for (SaveRoleRequestDto role : defaultRoles) {
                roleHandler.saveRole(role);
            }
        } else {
            log.info("Roles already created");
        }
    }

    private List<SaveRoleRequestDto> getDefaultRoles() {
        List<SaveRoleRequestDto> defaultRoles = new ArrayList<>();
        defaultRoles.add(new SaveRoleRequestDto("ROLE_ADMIN"));
        defaultRoles.add(new SaveRoleRequestDto("ROLE_OWNER"));
        defaultRoles.add(new SaveRoleRequestDto("ROLE_EMPLOYEE"));
        defaultRoles.add(new SaveRoleRequestDto("ROLE_CLIENT"));
        return defaultRoles;
    }
}
