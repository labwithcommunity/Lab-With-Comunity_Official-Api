package com.labwithcommunity.domain.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserBeanConfiguration {

    @Bean
     UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    UserFacade userFacade(UserService userService) {
        return new UserFacade(userService);
    }
}
