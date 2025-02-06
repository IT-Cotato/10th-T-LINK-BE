package org.cotato.tlinkserver.domain.room.application.dto.response;

import java.util.List;

import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.constant.Role;

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
	public static RoomResponse from(Registration registration) {
		Room room = registration.getRoom();
		User opponent = registration.getUser();

		if (opponent == null) {
			return RoomResponse.builder()
				.roomId(room.getId())
				.roomName(registration.getRoomName())
				.subject(room.getSubject())
				.lessonDays(room.getLessonDays().stream().map(lessonDay -> lessonDay.getLessonDay().getInKorean()).toList())
				.build();
		}

		if (opponent.getRole().equals(Role.TEACHER)) {
			return RoomResponse.builder()
				.roomId(room.getId())
				.roomName(registration.getRoomName())
				.subject(room.getSubject())
				.lessonDays(room.getLessonDays().stream().map(lessonDay -> lessonDay.getLessonDay().getInKorean()).toList())
				.opponent(OpponentResponse.from(opponent, opponent.getUsername()))
				.build();
		}
		else {
			return RoomResponse.builder()
				.roomId(room.getId())
				.roomName(registration.getRoomName())
				.subject(room.getSubject())
				.lessonDays(room.getLessonDays().stream().map(lessonDay -> lessonDay.getLessonDay().getInKorean()).toList())
				.opponent(OpponentResponse.from(opponent, room.getStudentName()))
				.build();
		}
	}
}
