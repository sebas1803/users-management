package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.UserModel;

import java.util.Optional;

public interface IUserServicePort {
    void saveUser(UserModel userModel);
    UserModel findById(Long id);
    Optional<UserModel> findByEmail(String email);
}
