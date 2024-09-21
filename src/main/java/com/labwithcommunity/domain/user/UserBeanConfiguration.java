package com.labwithcommunity.domain.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class UserBeanConfiguration {

    @Bean
     UserService userService(UserRepository userRepository,  PasswordEncoder passwordEncoder) {
        return new UserService(userRepository, passwordEncoder);
    }

    @Bean
    UserFacade userFacade(UserService userService) {
        return new UserFacade(userService);
    }
}
