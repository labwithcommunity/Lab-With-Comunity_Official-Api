package com.labwithcommunity.domain.user.exception;

import lombok.Getter;

@Getter
public enum UserExceptionMessages {

    USERNAME_ALREADY_EXIST("User already Exist"),
    USER_NOT_FOUND("User Not Found");
    final String message;

    UserExceptionMessages(String message) {
        this.message = message;
    }
}
