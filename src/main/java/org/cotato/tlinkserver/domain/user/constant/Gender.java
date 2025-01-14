package org.cotato.tlinkserver.domain.user.constant;

import lombok.Getter;

@Getter
public enum Gender {
	MALE("남"),
	FEMALE("여");

	private final String inKorean;

	Gender(String inKorean) {
		this.inKorean = inKorean;
	}
}
