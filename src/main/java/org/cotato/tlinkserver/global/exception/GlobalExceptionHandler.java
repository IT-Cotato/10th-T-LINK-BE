package org.cotato.tlinkserver.global.exception;

import java.util.NoSuchElementException;

import org.cotato.tlinkserver.api.dto.response.ErrorResponse;
import org.cotato.tlinkserver.global.util.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e){
		return makeErrorResponseEntity(HttpStatus.NOT_FOUND, ErrorMessage.NOT_FOUND.getDetailMessage());
	}

	private ResponseEntity<Object> makeErrorResponseEntity(HttpStatus httpStatus, String message) {
		return ResponseEntity
			.status(httpStatus)
			.body(ErrorResponse.of(httpStatus, message));
	}

}
