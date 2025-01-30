package org.cotato.tlinkserver.domain.room.application;

import java.util.List;

import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomDataResponse;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomModifyResponse;
import org.cotato.tlinkserver.domain.room.infra.repository.RegistrationRepository;
import org.springframework.stereotype.Service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RegistrationService {

	private final RegistrationRepository registrationRepository;

	public List<RoomDataResponse> getRooms(final Long userId) {
		return registrationRepository.findAllByUserId(userId).stream()
			.map(r -> RoomDataResponse.from(r.getRoom(), r.getName(), r.getUser()))
			.toList();
	}

	public RoomModifyResponse getRoomModify(final Long userId, final Long roomId) {
		List<Registration> registrations = registrationRepository.findAllByRoomId(roomId);

		Registration teacherRegistration = registrations.stream()
			.filter(r -> r.getUser().getId().equals(userId))
			.findFirst()
			.orElseThrow();
		Registration studentRegistration = registrations.stream()
			.filter(r -> !r.getUser().getId().equals(userId))
			.findFirst()
			.orElseThrow();

		return RoomModifyResponse.from(
			teacherRegistration.getRoom(),
			teacherRegistration.getName(),
			teacherRegistration,
			studentRegistration
		);

	}

}
