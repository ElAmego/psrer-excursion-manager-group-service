package org.zapovednik.excursionservice.exception.custom.error;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessage {
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "An unexpected error occurred";
}