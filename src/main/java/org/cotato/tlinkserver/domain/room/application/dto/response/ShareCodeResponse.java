package org.cotato.tlinkserver.domain.room.application.dto.response;

import lombok.Builder;

@Builder
public record ShareCodeResponse
	(
		String shareCode
	)
{
	public static ShareCodeResponse from(final String shareCode) {
		return ShareCodeResponse.builder()
			.shareCode(shareCode)
			.build();
	}
}
