package org.cotato.tlinkserver.domain.room.application.dto.response;

import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.user.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RoomDataResponse
	(
		@NotNull
		Room room,
		@NotBlank
		String roomName,
		User user
	)
{
	public static RoomDataResponse from(Registration registration) {
		return RoomDataResponse.builder()
			.room(registration.getRoom())
			.roomName(registration.getRoomName())
			.user(registration.getUser())
			.build();
	}
}
