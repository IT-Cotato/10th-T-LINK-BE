package org.cotato.tlinkserver.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cotato.tlinkserver.domain.token.RefreshToken;
import org.cotato.tlinkserver.domain.token.infra.RefreshTokenRepository;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.cotato.tlinkserver.global.exception.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReissueService {

    private final TokenParser tokenParser;
    private final JwtTokenValidator jwtTokenValidator;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenExtractor jwtTokenExtractor;
    private final JwtTokenGenerator jwtTokenGenerator;

    @Transactional
    public Token reissue(String refreshToken) {

        RefreshToken token = getValidRefreshToken(refreshToken);

        String userId = jwtTokenExtractor.getSubject(token.getRefreshToken());
        Role role = jwtTokenExtractor.getRole(token.getRefreshToken());
        String newAccessToken = jwtTokenGenerator.createAccessToken(userId, role);
        String newRefreshToken = jwtTokenGenerator.createRefreshToken(userId, role);

        refreshTokenRepository.save(
                RefreshToken.of(newRefreshToken, Long.parseLong(userId))
        );

        refreshTokenRepository.deleteAllByUserIdAndRefreshToken(
                Long.parseLong(userId), token.getRefreshToken()
        );

        return new Token(newAccessToken, newRefreshToken);
    }

    private RefreshToken getValidRefreshToken(String refreshToken) {
        String parsedToken = tokenParser.getToken(refreshToken);
        jwtTokenValidator.validate(parsedToken);
        return refreshTokenRepository.findByRefreshToken(parsedToken)
                .orElseThrow(UnauthorizedException::wrong);
    }

    @Transactional(readOnly = true)
    public Token regenerateToken(long userId, Role role) {
        return new Token(
                jwtTokenGenerator.createAccessToken(String.valueOf(userId), role),
                jwtTokenGenerator.createRefreshToken(String.valueOf(userId), role)
        );
    }
}
