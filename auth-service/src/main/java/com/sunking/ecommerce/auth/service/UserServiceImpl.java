package com.sunking.ecommerce.auth.service;

import com.sunking.ecommerce.auth.dto.UserDto;
import com.sunking.ecommerce.auth.entity.UserEntity;
import com.sunking.ecommerce.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto user) {
        UserEntity userEntity = UserEntity.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
        userEntity = userRepository.saveAndFlush(userEntity);
        return new UserDto(userEntity);
    }
}
