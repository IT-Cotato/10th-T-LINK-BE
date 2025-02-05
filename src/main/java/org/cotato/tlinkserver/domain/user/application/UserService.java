package org.cotato.tlinkserver.domain.user.application;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.infra.repository.UserRepository;
import org.cotato.tlinkserver.global.exception.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public boolean existUserById(final long userId) {
        return userRepository.existsById(userId);
    }

    @Transactional(readOnly = true)
    public User getValidUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UnauthorizedException::wrong);
    }
}
