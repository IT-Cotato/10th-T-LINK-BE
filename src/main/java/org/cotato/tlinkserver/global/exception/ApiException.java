package org.cotato.tlinkserver.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    private final HttpStatus httpStatus;

    public ApiException(
            HttpStatus httpStatus,
            String message
    ) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public static ApiException of(
            HttpStatus httpStatus,
            String message
    ) {
        return new ApiException(httpStatus, message);
    }
}
