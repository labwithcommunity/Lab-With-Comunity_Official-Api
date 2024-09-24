package com.labwithcommunity.domain.user.exception;

import lombok.Getter;

import java.util.ResourceBundle;

@Getter
public enum UserExceptionMessages {

    USERNAME_ALREADY_EXIST("user.already.exist"),
    TECHNOLOGY_NOT_FOUND("user.technology.not.found"),
    USER_NOT_FOUND("user.not.found");

    final String message;

    private final  ResourceBundle messagesBundle = ResourceBundle.getBundle("validationMessages");

    UserExceptionMessages(String messageKey) {
        this.message = messagesBundle.getString(messageKey);
    }
}