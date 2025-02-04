package org.cotato.tlinkserver.domain.room.constant;

import lombok.Getter;

@Getter
public enum DayOfWeek {
	MONDAY("월"),
	TUESDAY("화"),
	WEDNESDAY("수"),
	THURSDAY("목"),
	FRIDAY("금"),
	SATURDAY("토"),
	SUNDAY("일");

	private final String inKorean;

	DayOfWeek(String inKorean) {
		this.inKorean = inKorean;
	}
}
