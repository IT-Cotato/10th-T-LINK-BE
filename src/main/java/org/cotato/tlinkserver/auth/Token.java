package org.cotato.tlinkserver.auth;

public record Token(
        String accessToken,
        String refreshToken
) {
}
