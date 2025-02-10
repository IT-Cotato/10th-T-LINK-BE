package org.cotato.tlinkserver.domain.room.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;

import org.cotato.tlinkserver.global.exception.TLinkException;
import org.cotato.tlinkserver.global.message.ErrorMessage;

import com.fasterxml.jackson.annotation.JsonCreator;

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

	@JsonValue
	private final String inKorean;

	DayOfWeek(String inKorean) {
		this.inKorean = inKorean;
	}

	@JsonCreator
	public static DayOfWeek from(final String input) {
		return Arrays.stream(DayOfWeek.values())
			.filter(day -> day.inKorean.equals(input))
			.findFirst()
			.orElseThrow(
				() -> new TLinkException(ErrorMessage.BAD_REQUEST)
			);
	}

}
