package org.cotato.tlinkserver.auth;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.domain.token.infra.RefreshTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LogoutService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void logout(long userId) {
        refreshTokenRepository.deleteAllByUserId(userId);
    }
}
