package org.cotato.tlinkserver.global.exception;

import org.cotato.tlinkserver.global.message.ErrorMessage;

public class ConflictException extends TLinkException {
    public ConflictException(ErrorMessage errorMessage) { super(errorMessage); }
}