package org.cotato.tlinkserver.domain.user.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.Getter;
import org.cotato.tlinkserver.global.exception.TLinkException;
import org.cotato.tlinkserver.global.message.ErrorMessage;

@Getter
public enum Gender {
	MALE("남성"),
	FEMALE("여성");

	@JsonValue
	private final String inKorean;

	Gender(String inKorean) {
		this.inKorean = inKorean;
	}

	@JsonCreator
	public static Gender from(final String input) {
		return Arrays.stream(Gender.values())
				.filter(role -> role.inKorean.equals(input))
				.findFirst()
				.orElseThrow(
						() -> new TLinkException(ErrorMessage.BAD_REQUEST)
				);
	}
}
