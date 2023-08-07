package org.gomez.springproyectouniverisdad_byrongomez.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
