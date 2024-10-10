package com.labwithcommunity.domain.user.exception;

import lombok.Getter;

import java.util.ResourceBundle;

@Getter
public enum UserExceptionMessages {

    USERNAME_ALREADY_EXIST("user.already.exist"),
    TECHNOLOGY_NOT_FOUND("user.technology.not.found"),
    USER_NOT_FOUND("user.not.found"),
    PASSWORDS_DO_NOT_MATCH("user.passwords.do.not.match"),
    EMAIL_ALREADY_EXIST("user.email.already.exist");
    final String message;

    private final  ResourceBundle messagesBundle = ResourceBundle.getBundle("validationMessages");

    UserExceptionMessages(String messageKey) {
        this.message = messagesBundle.getString(messageKey);
    }
}