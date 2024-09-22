package com.sunking.ecommerce.auth.config;

import com.sunking.ecommerce.auth.dto.UserDto;
import com.sunking.ecommerce.auth.repository.UserRepository;
import com.sunking.ecommerce.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // Check if the database is empty before adding users
        if (userRepository.count() == 0) {
            userService.createUser(UserDto.builder().email("user1@email.com").password("user1pwd").build());
            userService.createUser(UserDto.builder().email("user2@email.com").password("user2pwd").build());
            userService.createUser(UserDto.builder().email("user3@email.com").password("user3pwd").build());
        }
    }
}
