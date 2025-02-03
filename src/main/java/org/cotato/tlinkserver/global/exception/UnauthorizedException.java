package org.cotato.tlinkserver.global.exception;

import org.cotato.tlinkserver.global.message.ErrorMessage;

public class UnauthorizedException extends TLinkException {
    public UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

    public static UnauthorizedException wrong() {
        return new UnauthorizedException(ErrorMessage.INVALID_TOKEN);
    }

    public static UnauthorizedException expired() {
        return new UnauthorizedException(ErrorMessage.EXPIRED_TOKEN);
    }

    public static UnauthorizedException empty() {
        return new UnauthorizedException(ErrorMessage.EMPTY_TOKEN);
    }
}
