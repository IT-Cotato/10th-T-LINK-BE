package org.cotato.tlinkserver.domain.room.application.dto.response;

import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.user.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import software.amazon.awssdk.annotations.NotNull;

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
