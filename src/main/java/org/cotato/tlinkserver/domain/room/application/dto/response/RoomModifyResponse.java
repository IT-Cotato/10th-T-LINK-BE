package org.cotato.tlinkserver.domain.room.application.dto.response;

import java.util.List;

import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RoomModifyResponse
	(
		@NotNull
		Long roomId,
		@NotBlank
		String roomName,
		@NotBlank
		String studentName,
		@NotBlank
		String subject,
		@NotNull
		List<LessonDayModifyResponse> lessonDays,
		@NotNull
		PermissionResponse parentPermission,
		@NotNull
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
