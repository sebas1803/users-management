package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.powerup.infrastructure.utility.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void saveUser(UserModel userModel) {
        UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(userModel));
        String encryptedPassword = UserUtil.encryptPassword(userModel.getPassword());
        userEntity.setPassword(encryptedPassword);
        userEntityMapper.toUserModel(userEntity);
    }

    @Override
    public UserModel findById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        return userEntityMapper.toUserModel(userEntity);
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
        return Optional.ofNullable(userEntityMapper.toUserModel(userEntity));
    }
}
