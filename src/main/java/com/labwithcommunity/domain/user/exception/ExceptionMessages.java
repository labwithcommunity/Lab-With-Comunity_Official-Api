package com.labwithcommunity.domain.user.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessages {

    NICKNAME_ALREADY_EXIST("User already Exist");
    final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }
}
