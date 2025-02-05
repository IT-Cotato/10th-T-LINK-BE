package org.cotato.tlinkserver.domain.user.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import lombok.Getter;
import org.cotato.tlinkserver.global.exception.TLinkException;
import org.cotato.tlinkserver.global.message.ErrorMessage;

@Getter
public enum Role {
	ONBOARDING("온보딩"),
	TEACHER("선생님"),
	PARENT("학부모"),
	STUDENT("학생");

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
