package org.cotato.tlinkserver.domain.counselingLog.constant;

import lombok.Getter;

@Getter
public enum Engagement {
	UPPER("상"),
	MIDDLE("중"),
	LOWER("하");

	private final String inKorean;

	Engagement(String inKorean) {
		this.inKorean = inKorean;
	}
}