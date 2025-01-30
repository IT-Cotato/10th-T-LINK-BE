package org.cotato.tlinkserver.domain.room.application.dto.response;

import java.util.List;

import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.user.User;

import lombok.Builder;

@Builder
public record RoomResponse
	(
		Long roomId,
		String roomName,
		String subject,
		List<String> lessonDays,
		OpponentResponse opponent
	)
{
	public static RoomResponse from(Room room, String roomName, User user) {
		return RoomResponse.builder()
			.roomId(room.getId())
			.roomName(roomName)
			.subject(room.getSubject())
			.lessonDays(room.getLessonDays().stream().map(lessonDay -> lessonDay.getLessonDay().getInKorean()).toList())
			.opponent(OpponentResponse.from(user))
			.build();
	}
}
