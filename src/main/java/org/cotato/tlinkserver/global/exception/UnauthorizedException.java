package org.cotato.tlinkserver.global.exception;

import org.cotato.tlinkserver.global.message.ErrorMessage;

public class UnauthorizedException extends TLinkException {
    public UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
