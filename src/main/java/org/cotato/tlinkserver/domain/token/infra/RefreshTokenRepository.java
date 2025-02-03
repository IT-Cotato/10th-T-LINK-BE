package org.cotato.tlinkserver.domain.token.infra;

import java.util.Optional;
import org.cotato.tlinkserver.domain.token.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    void deleteAllByUserId(long userId);

    void deleteAllByUserIdAndRefreshToken(long userId, String refreshToken);
}
