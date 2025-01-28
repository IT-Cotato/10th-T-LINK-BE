package org.cotato.tlinkserver.global.exception;

import lombok.Getter;
import org.cotato.tlinkserver.global.message.ErrorMessage;

@Getter
public class TLinkException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public TLinkException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
