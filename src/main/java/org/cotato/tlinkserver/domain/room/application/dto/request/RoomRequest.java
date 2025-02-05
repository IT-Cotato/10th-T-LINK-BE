package org.cotato.tlinkserver.domain.room.application.dto.request;

import java.util.List;

import org.cotato.tlinkserver.domain.room.LessonDay;
import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.constant.DayOfWeek;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.constant.Role;

import lombok.Builder;

@Builder
public record RoomRequest
	(
		String roomName,
		String studentName,
		String subject,
		List<String> lessonDays,
		PermissionRequest parentPermission,
		PermissionRequest studentPermission
	)
{
	public Room save(User user) {
		Room room = Room.builder()
			.studentName(studentName)
			.subject(subject)
			.build();

		Registration teacherRegistration = Registration.builder()
			.roomName(roomName)
			.role(Role.TEACHER)
			.lectureFile(true)
			.homework(true)
			.gradeStatistic(true)
			.counselingLog(true)
			.deposit(true)
			.build();
		user.addRegistration(teacherRegistration);
		room.addRegistration(teacherRegistration);

		Registration parentRegistration = parentPermission.create(Role.PARENT, roomName);
		room.addRegistration(parentRegistration);

		Registration studentRegistration = studentPermission.create(Role.STUDENT, roomName);
		room.addRegistration(studentRegistration);

		return room;
	}

	public void modify(Room room, Registration parentRegistration, Registration studentRegistration) {
		room.setStudentName(studentName);
		room.setSubject(subject);

		room.getLessonDays().clear();
		lessonDays.stream()
			.map(lessonDay -> LessonDay.builder().lessonDay(DayOfWeek.toEnum(lessonDay)).build())
			.toList()
			.forEach(room::addLessonDay);

		parentPermission.modify(parentRegistration, roomName);
		studentPermission.modify(studentRegistration, roomName);
	}
}
