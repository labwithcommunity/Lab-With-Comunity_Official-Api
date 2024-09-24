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
    TechnologyRegistryService technologyRegistryService(UserService userService) {
        return new TechnologyRegistryService(userService);
    }

    @Bean
    UserFacade userFacade(UserService userService, TechnologyRegistryService technologyRegistryService) {
        return new UserFacade(userService, technologyRegistryService);
    }
}
