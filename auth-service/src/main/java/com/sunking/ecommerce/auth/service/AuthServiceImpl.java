package com.sunking.ecommerce.auth.service;

import com.sunking.ecommerce.auth.dto.request.LoginRequestDto;
import com.sunking.ecommerce.auth.dto.response.LoginResponseDto;
import com.sunking.ecommerce.auth.entity.UserEntity;
import com.sunking.ecommerce.auth.repository.UserRepository;
import com.sunking.ecommerce.auth.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
        if (optionalUser.isEmpty())
            throw new RuntimeException("User doesn't exist");

        UserEntity user = optionalUser.get();
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid credentials");

        final String jwt = jwtUtil.generateToken(user.getId().toString());
        return new LoginResponseDto(jwt);
    }

}
