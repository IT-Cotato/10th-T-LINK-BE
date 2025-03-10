package org.cotato.tlinkserver.domain.room.application.dto.response;

import java.util.List;

import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;

import lombok.Builder;

@Builder
public record RoomModifyResponse
	(
		Long roomId,
		String roomName,
		String studentName,
		String subject,
		List<LessonDayModifyResponse> lessonDays,
		PermissionResponse parentPermission,
		PermissionResponse studentPermission
	)
{
	public static RoomModifyResponse from(final Room room, final String roomName, final Registration parentRegistration, final Registration studentRegistration) {
		return RoomModifyResponse.builder()
			.roomId(room.getId())
			.roomName(roomName)
			.studentName(room.getStudentName())
			.subject(room.getSubject())
			.lessonDays(room.getLessonDays().stream().map(LessonDayModifyResponse::from).toList())
			.parentPermission(PermissionResponse.from(parentRegistration))
			.studentPermission(PermissionResponse.from(studentRegistration))
			.build();
	}
}
