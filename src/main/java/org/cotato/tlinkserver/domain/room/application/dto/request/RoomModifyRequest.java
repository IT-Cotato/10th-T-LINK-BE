package org.cotato.tlinkserver.domain.room.application.dto.request;

import java.util.List;

import org.cotato.tlinkserver.api.facade.dto.request.RoomLessonDayDTO;
import org.cotato.tlinkserver.domain.room.LessonDay;
import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoomModifyRequest
	(
		@NotBlank
		String roomName,
		@NotBlank
		String studentName,
		@NotBlank
		String subject,
		@NotNull
		List<RoomLessonDayDTO> lessonDays,
		@NotNull
		PermissionRequest parentPermission,
		@NotNull
		PermissionRequest studentPermission
	)
{

	public void modify(Registration registration) {
		registration.setRoomName(roomName);
	}

	public void modify(Room room, Registration teacherRegistration, Registration parentRegistration, Registration studentRegistration) {
		room.setStudentName(studentName);
		room.setSubject(subject);
		room.getLessonDays().clear();
		lessonDays().forEach(day -> {
			LessonDay lessonDay = new LessonDay(day.lessonDay());
			room.addLessonDay(lessonDay);
		});

		teacherRegistration.setRoomName(roomName);
		parentPermission.modify(parentRegistration);
		studentPermission.modify(studentRegistration);
	}
}
