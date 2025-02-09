package org.cotato.tlinkserver.domain.room.application.dto.request;

import java.util.List;

import org.cotato.tlinkserver.api.facade.dto.request.RoomLessonDayDTO;
import org.cotato.tlinkserver.domain.room.LessonDay;
import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.constant.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RoomSaveRequest
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
	public Room save(User user) {
		Room room = Room.builder()
			.studentName(studentName).subject(subject)
			.build();

		lessonDays.forEach(day -> {
			LessonDay lessonDay = new LessonDay(day.lessonDay());
			room.addLessonDay(lessonDay);
		});

		Registration teacherRegistration = Registration.builder()
			.roomName(roomName).role(Role.TEACHER).lectureFile(true).homework(true)
			.gradeStatistic(true).counselingLog(true).deposit(true)
			.build();
		user.addRegistration(teacherRegistration);
		room.addRegistration(teacherRegistration);

		Registration parentRegistration = parentPermission.create(Role.PARENT, roomName);
		room.addRegistration(parentRegistration);

		Registration studentRegistration = studentPermission.create(Role.STUDENT, roomName);
		room.addRegistration(studentRegistration);

		return room;
	}

}
