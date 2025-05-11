package org.cotato.tlinkserver.global.exception;

import org.cotato.tlinkserver.global.message.ErrorMessage;

public class HttpRequestMethodNotSupportedException extends TLinkException {
    public HttpRequestMethodNotSupportedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
