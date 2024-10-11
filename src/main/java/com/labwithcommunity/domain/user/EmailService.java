package com.labwithcommunity.domain.user;

public interface EmailService {

    void sendRegisterEmail(String recipient, String token);

}
