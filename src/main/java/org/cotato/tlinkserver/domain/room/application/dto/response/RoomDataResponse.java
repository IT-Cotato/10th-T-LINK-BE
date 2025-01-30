package org.cotato.tlinkserver.domain.room.application.dto.response;

import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.user.User;

import lombok.Builder;

@Builder
public record RoomDataResponse
	(
		Room room,
		String roomName,
		User user
	)
{
	public static RoomDataResponse from(Room room, String roomName, User user) {
		return RoomDataResponse.builder()
			.room(room)
			.roomName(roomName)
			.user(user)
			.build();
	}
}
