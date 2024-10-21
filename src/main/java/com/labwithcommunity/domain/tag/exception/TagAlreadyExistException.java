package com.labwithcommunity.domain.tag.exception;

public class TagAlreadyExistException extends RuntimeException {
    public TagAlreadyExistException(String message) {
        super(message);
    }
}
