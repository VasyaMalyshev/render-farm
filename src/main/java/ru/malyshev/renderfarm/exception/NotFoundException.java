package ru.malyshev.renderfarm.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message, null, false, false);
    }
}
