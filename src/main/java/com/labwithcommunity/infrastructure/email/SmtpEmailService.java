package com.labwithcommunity.infrastructure.email;

import com.labwithcommunity.domain.user.EmailService;
import com.labwithcommunity.domain.user.TokenEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService implements EmailService {
    private final JavaMailSender mailSender;

    @Value("${email.sender}")
    private String emailSender;

    @Value("${change.password.url}")
    private String changePasswordUrl;

    public SmtpEmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendRegisterEmail(String recipient, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setFrom(emailSender);
        message.setSubject("Registration Confirmation");
        message.setText("Please confirm your registration by clicking the following link: "+ changePasswordUrl + token);
        mailSender.send(message);
    }
}