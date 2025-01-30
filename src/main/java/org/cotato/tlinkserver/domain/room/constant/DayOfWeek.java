package org.cotato.tlinkserver.domain.room.constant;

import java.util.HashMap;
import java.util.Map;

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
	private static final Map<String, DayOfWeek> map = new HashMap<>();
	static {
		for (DayOfWeek day : DayOfWeek.values()) {
			map.put(day.inKorean, day);
		}
	}

	DayOfWeek(String inKorean) {
		this.inKorean = inKorean;
	}

	public static DayOfWeek toEnum(String inKorean) {
		return map.get(inKorean);
	}

}
