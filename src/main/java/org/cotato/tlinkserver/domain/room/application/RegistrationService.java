package org.cotato.tlinkserver.domain.room.application;

import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.infra.repository.RegistrationRepository;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.cotato.tlinkserver.global.exception.NotFoundException;
import org.cotato.tlinkserver.global.exception.UnauthorizedException;
import org.cotato.tlinkserver.global.message.ErrorMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new UnauthorizedException(ErrorMessage.UNAUTHORIZED));
    }

    public List<Registration> getRegistrations(final Long userId) {
        return registrationRepository.findRegistrationsByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<Registration> getRegistrationsWithRoomInfo(long userId) {
        return registrationRepository.findFetchRegistrationsByUserId(userId);
    }
}
