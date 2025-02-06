package org.cotato.tlinkserver.domain.room.application.dto.request;

import java.util.List;

import org.cotato.tlinkserver.domain.room.LessonDay;
import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.constant.DayOfWeek;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.constant.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RoomRequest
	(
		@NotBlank
		String roomName,
		@NotBlank
		String studentName,
		@NotBlank
		String subject,
		@NotNull
		List<DayOfWeek> lessonDays,
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

		lessonDays.forEach(day -> room.addLessonDay(new LessonDay(day)));

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

	public void modify(Registration registration) {
		registration.setRoomName(roomName);
	}

	public void modify(Room room, Registration teacherRegistration, Registration parentRegistration, Registration studentRegistration) {
		room.setStudentName(studentName);
		room.setSubject(subject);
		room.getLessonDays().clear();
		lessonDays().forEach(day -> {
			LessonDay lessonDay = new LessonDay(day);
			room.addLessonDay(lessonDay);
		});

		teacherRegistration.setRoomName(roomName);
		parentPermission.modify(parentRegistration);
		studentPermission.modify(studentRegistration);
	}
}
