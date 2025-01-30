package org.cotato.tlinkserver.domain.room.application.dto.response;

import org.cotato.tlinkserver.domain.user.User;

import lombok.Builder;

@Builder
public record OpponentResponse
	(
		Long id,
		String name,
		String gender,
		String backgroundColor
	)
{
	public static OpponentResponse from(User user) {
		return OpponentResponse.builder()
			.id(user.getId())
			.name(user.getUsername())
			.gender(user.getGender().getInKorean())
			.backgroundColor(user.getBackgroundColor())
			.build();
	}
}
