package com.labwithcommunity.domain.user;

import com.labwithcommunity.infrastructure.email.EmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class UserBeanConfiguration {

    @Bean
    UserFacade userFacade(UserRepository userRepository, PasswordEncoder passwordEncoder, ConfirmationsService confirmationsService, EmailService emailService, TokenEmailService tokenEmailService, TokenResetPasswordService tokenResetPasswordService) {
        UserFinderService userFinderService = new UserFinderService(userRepository);
        UserRegistrationService userRegistrationService = new UserRegistrationService(userRepository, passwordEncoder, confirmationsService, emailService, tokenEmailService, tokenResetPasswordService);
        return new UserFacade(userRegistrationService, userFinderService);
    }

    @Bean
    UserRegistration userRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder, ConfirmationsService confirmationsService, EmailService emailService, TokenEmailService tokenEmailService, TokenResetPasswordService tokenResetPasswordService) {
        return new UserRegistrationService(userRepository, passwordEncoder, confirmationsService, emailService, tokenEmailService, tokenResetPasswordService);
    }

    @Bean
    LoginSuccessListener loginSuccessListener(UserRepository userRepository) {
        return new LoginSuccessListener(userRepository);
    }
    @Bean
    ConfirmationsService confirmationsService(ConfirmationsRepository confirmationsRepository) {
        return new ConfirmationsService(confirmationsRepository);
    }
 }
