package org.zapovednik.excursionservice.exception.custom.error;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorType {
    public static final String NOT_FOUND = "Not Found";
    public static final String VALIDATION_ERROR = "Validation Error";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
}