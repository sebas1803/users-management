package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.UserModel;

import java.util.Optional;

public interface IUserPersistencePort {
    void saveUser(UserModel userModel);
    UserModel findById(Long id);
    Optional<UserModel> findByEmail(String email);
}
