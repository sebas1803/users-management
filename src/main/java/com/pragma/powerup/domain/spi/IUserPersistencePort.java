package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface IUserPersistencePort {
    void saveUser(UserModel userModel);

    UserModel findById(Long id);

    UserModel findByEmail(String email);
}
