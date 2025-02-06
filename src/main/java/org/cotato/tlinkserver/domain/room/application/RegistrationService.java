package org.cotato.tlinkserver.domain.room.application;

import java.util.List;

import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomModifyResponse;
import org.cotato.tlinkserver.domain.room.infra.repository.RegistrationRepository;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.cotato.tlinkserver.global.exception.NotFoundException;
import org.cotato.tlinkserver.global.message.ErrorMessage;
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
		return registrationRepository.findRegistrationByRoomIdAndRole(roomId, role)
			.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
	}

	public Registration getRegistration(final Long userId, final Long roomId) {
		return registrationRepository.findRegistrationByUserIdAndRoomId(userId, roomId)
			.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
	}

	public List<Registration> getRegistrations(final Long userId) {
		return registrationRepository.findRegistrationsByUserId(userId);
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

}
