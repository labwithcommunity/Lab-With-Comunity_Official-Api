package com.labwithcommunity.infrastructure.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
class EmailServiceConfiguration {

    @Bean
    public EmailService emailService(JavaMailSender javaMailSender) {
        return new SmtpEmailService(javaMailSender);
    }
}
