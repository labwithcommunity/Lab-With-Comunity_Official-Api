package com.labwithcommunity.domain.technology.exception;

public class TechnologyAlreadyExistException extends RuntimeException{
    public TechnologyAlreadyExistException(String message) {
        super(message);
    }
}
