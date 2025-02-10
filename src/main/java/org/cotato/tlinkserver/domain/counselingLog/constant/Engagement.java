package org.cotato.tlinkserver.domain.counselingLog.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;

import org.cotato.tlinkserver.global.exception.TLinkException;
import org.cotato.tlinkserver.global.message.ErrorMessage;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;

@Getter
public enum Engagement {
	UPPER("상"),
	MIDDLE("중"),
	LOWER("하");

	@JsonValue
	private final String inKorean;

	Engagement(String inKorean) {
		this.inKorean = inKorean;
	}

	@JsonCreator
	public static Engagement from(final String input) {
		return Arrays.stream(Engagement.values())
			.filter(engagement -> engagement.inKorean.equals(input))
			.findFirst()
			.orElseThrow(
				() -> new TLinkException(ErrorMessage.BAD_REQUEST)
			);
	}
}