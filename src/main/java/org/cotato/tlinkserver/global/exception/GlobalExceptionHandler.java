package org.cotato.tlinkserver.global.exception;

import org.cotato.tlinkserver.global.common.BaseResponse;
import org.cotato.tlinkserver.global.message.ErrorMessage;
import org.cotato.tlinkserver.global.util.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<BaseResponse<?>> handleIllegalArgumentException(IllegalArgumentException e) {
		return ApiResponseUtil.failure(ErrorMessage.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<BaseResponse<?>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		return ApiResponseUtil.failure(ErrorMessage.TYPE_MISMATCH);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<BaseResponse<?>> handleUnauthorizedException(UnauthorizedException e) {
		return ApiResponseUtil.failure(ErrorMessage.UNAUTHORIZED);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<BaseResponse<?>> handleNotFoundException(NotFoundException e) {
		return ApiResponseUtil.failure(ErrorMessage.NOT_FOUND);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<BaseResponse<?>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		return ApiResponseUtil.failure(ErrorMessage.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<BaseResponse<?>> handleConflictException(ConflictException e) {
		return ApiResponseUtil.failure(ErrorMessage.CONFLICT);
	}

	@ExceptionHandler(TLinkException.class)
	public ResponseEntity<BaseResponse<?>> handleTLinkException(TLinkException e) {
		return ApiResponseUtil.failure(e.getErrorMessage());
	}
}
