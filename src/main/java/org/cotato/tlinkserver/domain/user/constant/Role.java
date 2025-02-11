package org.cotato.tlinkserver.domain.user.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.Getter;
import org.cotato.tlinkserver.global.exception.TLinkException;
import org.cotato.tlinkserver.global.message.ErrorMessage;

@Getter
public enum Role {
	ONBOARDING("ONBOARDING"),
	TEACHER("TEACHER"),
	PARENT("PARENT"),
	STUDENT("STUDENT");

	@JsonValue
	private final String inKorean;

	Role(String inKorean) {
		this.inKorean = inKorean;
	}

	@JsonCreator
	public static Role from(final String input) {
		return Arrays.stream(Role.values())
				.filter(role -> role.inKorean.equals(input))
				.findFirst()
				.orElseThrow(
						() -> new TLinkException(ErrorMessage.BAD_REQUEST)
				);
	}
}
