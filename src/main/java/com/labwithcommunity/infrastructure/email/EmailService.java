package com.labwithcommunity.infrastructure.email;

public interface EmailService {

    void sendRegisterEmail(String recipient, String token);

    void sendPasswordResetEmail(String recipient, String passwordResetToken);
}
