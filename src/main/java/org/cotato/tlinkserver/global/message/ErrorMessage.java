package org.cotato.tlinkserver.global.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
	/* 400 Bad Request */
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "요청 형식이 올바르지 않습니다."),
	TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "올바르지 않은 쿼리 파라미터 형식입니다."),

	/* 401 Unauthorized */
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "사용자의 로그인 검증을 실패했습니다."),
	INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 토큰입니다."),
	EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
	EMPTY_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 없습니다."),

	/* 403 Forbidden*/
	FORBIDDEN(HttpStatus.FORBIDDEN, "리소스 접근 권한이 없습니다."),

	/* 404 Not Found */
	NOT_FOUND(HttpStatus.NOT_FOUND, "요청하는 리소스가 존재하지 않습니다."),

	/* 405 Method Not Allowed */
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "잘못된 HTTP Method 요청입니다."),

	/* 409 Conflict */
	CONFLICT(HttpStatus.CONFLICT, "이미 존재하는 리소스입니다."),

	/* 500 Internal Server Error*/
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),;

	private final HttpStatus httpStatus;
	private final String message;
}
