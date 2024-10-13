package com.labwithcommunity.infrastructure.email;

public interface EmailService {

    void sendRegisterEmail(String recipient, String token);

}
