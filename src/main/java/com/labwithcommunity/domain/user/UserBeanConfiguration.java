package com.labwithcommunity.domain.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class UserBeanConfiguration {

    @Bean
    TechnologyRegistryService technologyRegistryService(UserRepository userRepository) {
        UserFinderService userFinderService = new UserFinderService(userRepository);
        return new TechnologyRegistryService();
    }

    @Bean
    UserFacade userFacade(UserRepository userRepository, PasswordEncoder passwordEncoder, TechnologyRegistryService technologyRegistryService, ConfirmationsService confirmationsService) {
        UserFinderService userFinderService = new UserFinderService(userRepository);
        UserRegistrationService userRegistrationService = new UserRegistrationService(userRepository, passwordEncoder, confirmationsService);
        return new UserFacade(userRegistrationService, userFinderService, technologyRegistryService);
    }

    @Bean
    UserRegistration userRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder, ConfirmationsService confirmationsService) {
        return new UserRegistrationService(userRepository, passwordEncoder, confirmationsService);
    }
}
