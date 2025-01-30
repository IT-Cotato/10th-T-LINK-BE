package org.cotato.tlinkserver.domain.room.application;

import java.util.List;

import org.cotato.tlinkserver.domain.room.infra.repository.RegistrationRepository;
import org.cotato.tlinkserver.domain.user.User;
import org.springframework.stereotype.Service;

import jakarta.persistence.Tuple;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RegistrationService {

	private final RegistrationRepository registrationRepository;

	public List<Tuple> getRooms(Long userId) {
		return registrationRepository.findRoomsByUserId(userId);
	}

	public User getOpponent(Long roomId, Long userId) { return registrationRepository.findOpponent(roomId, userId); }

}
