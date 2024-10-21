package com.labwithcommunity.domain.tag.exception;

public class TagNotFoundException extends RuntimeException{
    public TagNotFoundException(String message) {
        super(message);
    }
}
