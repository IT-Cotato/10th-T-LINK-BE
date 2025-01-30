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
		PermissionResponse studentPermission,
		PermissionResponse teacherPermission
	)
{
	public static RoomModifyResponse from(final Room room, final String roomName, final Registration studentRegistration, final Registration teacherRegistration) {
		return RoomModifyResponse.builder()
			.roomId(room.getId())
			.roomName(roomName)
			.studentName(room.getStudentName())
			.subject(room.getSubject())
			.lessonDays(room.getLessonDays().stream().map(LessonDayModifyResponse::from).toList())
			.studentPermission(PermissionResponse.from(studentRegistration))
			.teacherPermission(PermissionResponse.from(teacherRegistration))
			.build();
	}
}
