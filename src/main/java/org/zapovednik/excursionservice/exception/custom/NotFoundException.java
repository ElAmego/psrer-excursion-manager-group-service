package org.zapovednik.excursionservice.exception.custom;

public class NotFoundException extends RuntimeException {
    public NotFoundException(final String message) {
        super(message);
    }
}