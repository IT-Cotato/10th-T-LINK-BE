package org.cotato.tlinkserver.api.dto.response;

import org.cotato.tlinkserver.global.util.ErrorMessage;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse extends BaseResponse {

	private final String message;

	private ErrorResponse(
		HttpStatus status,
		String message
	) {
		super(status);
		this.message = message;
	}

	public static ErrorResponse of(final HttpStatus httpStatus, final String message) {
		return new ErrorResponse(httpStatus, message);
	}

	public static ErrorResponse badRequest() {
		return new ErrorResponse(HttpStatus.BAD_REQUEST, ErrorMessage.BAD_REQUEST.getDetailMessage());
	}

	public static ErrorResponse unauthorized() {
		return new ErrorResponse(HttpStatus.UNAUTHORIZED, ErrorMessage.UNAUTHORIZED.getDetailMessage());
	}

	public static ErrorResponse forbidden() {
		return new ErrorResponse(HttpStatus.FORBIDDEN, ErrorMessage.FORBIDDEN.getDetailMessage());
	}

	public static ErrorResponse notFound() {
		return new ErrorResponse(HttpStatus.NOT_FOUND, ErrorMessage.NOT_FOUND.getDetailMessage());
	}

}
