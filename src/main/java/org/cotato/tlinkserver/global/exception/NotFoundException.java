package org.cotato.tlinkserver.global.exception;

import org.cotato.tlinkserver.global.message.ErrorMessage;

public class NotFoundException extends TLinkException {
    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
