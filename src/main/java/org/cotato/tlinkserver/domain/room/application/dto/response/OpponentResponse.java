package org.cotato.tlinkserver.domain.room.application.dto.response;

import org.cotato.tlinkserver.domain.user.User;

import lombok.Builder;

@Builder
public record OpponentResponse
	(
		Long id,
		String name,
		String gender
	)
{
	public static OpponentResponse from(final User user, final String name) {
		// 선생님이 과외방을 생성했는데, 학생이나 학부모가 과외방에 들어오지 않은 경우
		if (user == null) {
			return null;
		}
		return OpponentResponse.builder()
			.id(user.getId())
			.name(name)
			.gender(user.getGender().getInKorean())
			.build();
	}
}
