package com.labwithcommunity.infrastructure.configs;

import com.labwithcommunity.domain.user.EmailService;
import com.labwithcommunity.infrastructure.email.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class EmailServiceConfiguration {

    @Bean
    public EmailService emailService(JavaMailSender javaMailSender) {
        return new SmtpEmailService(javaMailSender);
    }
}
