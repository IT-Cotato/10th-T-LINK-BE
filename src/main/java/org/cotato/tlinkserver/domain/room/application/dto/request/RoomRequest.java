package org.cotato.tlinkserver.domain.room.application.dto.request;

import java.util.List;

import org.cotato.tlinkserver.domain.room.LessonDay;
import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.constant.DayOfWeek;
import org.cotato.tlinkserver.domain.user.User;

import lombok.Builder;

@Builder
public record RoomRequest
	(
		String roomName,
		String studentName,
		String subject,
		List<String> lessonDays,
		PermissionRequest teacherPermission,
		PermissionRequest studentPermission
	)
{
	public Room save(User user) {
		Room room = Room.builder()
			.studentName(studentName)
			.subject(subject)
			.build();

		Registration teacherRegistration = teacherPermission.create();
		teacherRegistration.setName(roomName);
		user.addRegistration(teacherRegistration);
		room.addRegistration(teacherRegistration);

		Registration studentRegistration = studentPermission.create();
		studentRegistration.setName(roomName);
		room.addRegistration(studentRegistration);

		return room;
	}

	public void modify(Room room, Registration teacherRegistration, Registration studentRegistration) {
		room.setStudentName(studentName);
		room.setSubject(subject);

		room.getLessonDays().clear();
		lessonDays.stream()
			.map(lessonDay -> LessonDay.builder().lessonDay(DayOfWeek.toEnum(lessonDay)).build())
			.toList()
			.forEach(room::addLessonDay);

		teacherPermission.modify(teacherRegistration);
		studentPermission.modify(studentRegistration);
		teacherRegistration.setName(studentName);
	}
}
