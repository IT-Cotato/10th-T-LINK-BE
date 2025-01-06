package org.cotato.tlinkserver.domain.user.constant;

import lombok.Getter;

@Getter
public enum Role {
	TEACHER("선생님"),
	PARENT("학부모"),
	STUDENT("학생");

	private final String inKorean;

	Role(String inKorean) {
		this.inKorean = inKorean;
	}
}
