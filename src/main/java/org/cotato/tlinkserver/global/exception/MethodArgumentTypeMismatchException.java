package org.cotato.tlinkserver.global.exception;

import org.cotato.tlinkserver.global.message.ErrorMessage;

public class MethodArgumentTypeMismatchException extends TLinkException {
    public MethodArgumentTypeMismatchException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
