package org.cotato.tlinkserver.global.util;

import lombok.Getter;

@Getter
public enum SuccessMessage {
	SUCCESS("요청이 성공했습니다"),
	CREATED("생성되었습니다"),
	MODIFIED("수정되었습니다"),
	DELETED("삭제되었습니다");

	private final String detailMessage;

	SuccessMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}
}
