package org.cotato.tlinkserver.domain.room.application;

import java.util.List;

import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.application.dto.request.RoomRequest;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomDataResponse;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomModifyResponse;
import org.cotato.tlinkserver.domain.room.infra.repository.RegistrationRepository;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.springframework.stereotype.Service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RegistrationService {

	private final RegistrationRepository registrationRepository;

	public Registration getRegistration(final Long roomId, final Role role) {
		return registrationRepository.findRegistrationByRoomIdAndRole(roomId, role);
	}

	public List<Registration> getRegistrations(final Long userId) {
		return registrationRepository.findRegistrationsByUserId(userId);
	}

	public List<RoomDataResponse> getRooms(final Long userId) {
		return registrationRepository.findRegistrationsByUserId(userId).stream()
			.map(RoomDataResponse::from)
			.toList();
	}

	public RoomModifyResponse getRoomModify(final Long roomId) {
		List<Registration> registrations = registrationRepository.findRegistrationsByRoomId(roomId);

		Registration teacherRegistration = registrations.stream()
			.filter(r -> r.getRole().equals(Role.TEACHER)).findFirst().get();
		Registration parentRegistration = registrations.stream()
			.filter(r -> r.getRole().equals(Role.PARENT)).findFirst().get();
		Registration studentRegistration = registrations.stream()
			.filter(r -> r.getRole().equals(Role.STUDENT)).findFirst().get();

		return RoomModifyResponse.from(
			teacherRegistration.getRoom(),
			teacherRegistration.getRoomName(),
			parentRegistration,
			studentRegistration
		);

	}

	public void modifyRoom(final Long userId, final Long roomId, final RoomRequest roomRequest) {
		List<Registration> registrations = registrationRepository.findRegistrationsByRoomId(roomId);

		Registration teacherRegistration = registrations.stream()
			.filter(r -> r.getUser().getId().equals(userId))
			.findFirst()
			.orElseThrow();
		Registration studentRegistration = registrations.stream()
			.filter(r -> !r.getUser().getId().equals(userId))
			.findFirst()
			.orElseThrow();
		Room room = teacherRegistration.getRoom();

		roomRequest.modify(room, teacherRegistration, studentRegistration);
	}

}
