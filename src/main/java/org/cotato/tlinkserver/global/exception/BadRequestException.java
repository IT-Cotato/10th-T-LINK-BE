package org.cotato.tlinkserver.global.exception;

import org.cotato.tlinkserver.global.message.ErrorMessage;

public class BadRequestException extends TLinkException {
	public BadRequestException(ErrorMessage errorMessage) {
		super(errorMessage);
	}

	public static BadRequestException wrong() {
		return new BadRequestException(ErrorMessage.BAD_REQUEST);
	}
}
