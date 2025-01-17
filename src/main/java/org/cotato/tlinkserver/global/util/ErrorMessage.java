package org.cotato.tlinkserver.global.util;

import lombok.Getter;

@Getter
public enum ErrorMessage {
	BAD_REQUEST("요청 형식이 잘못 되었습니다"),
	UNAUTHORIZED("사용자의 로그인 검증을 실패했습니다"),
	FORBIDDEN("권한이 없습니다"),
	NOT_FOUND("요청한 자원이 존재하지 않습니다");

	private final String detailMessage;

	ErrorMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}
}
