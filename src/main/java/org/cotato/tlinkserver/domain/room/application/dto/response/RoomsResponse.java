package org.cotato.tlinkserver.domain.room.application.dto.response;

import java.util.List;

import lombok.Builder;

@Builder
public record RoomsResponse
	(
		List<RoomResponse> rooms
	)
{
	public static RoomsResponse from(List<RoomResponse> rooms) {
		return RoomsResponse.builder()
			.rooms(rooms)
			.build();
	}
}
