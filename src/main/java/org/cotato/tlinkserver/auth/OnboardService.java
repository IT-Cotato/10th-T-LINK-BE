package org.cotato.tlinkserver.auth;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.auth.command.OnboardCommand;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.infra.repository.UserRepository;
import org.cotato.tlinkserver.global.exception.ConflictException;
import org.cotato.tlinkserver.global.exception.UnauthorizedException;
import org.cotato.tlinkserver.global.message.ErrorMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OnboardService {

    private final UserRepository userRepository;

    @Transactional
    public void addUserInfo(long userId, OnboardCommand command) {
        validatePhoneNumber(command.phoneNumber());
        User user = getValidUser(userId);
        user.addOnboardInfo(command);
    }

    private User getValidUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UnauthorizedException::wrong);
    }

    private void validatePhoneNumber(String phoneNumber) {
        userRepository.findByPhoneNumber(phoneNumber)
                .ifPresent(p -> {
                    throw new ConflictException(ErrorMessage.DUPLICATED_PHONE_NUMBER);
                });
    }
}
