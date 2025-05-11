package org.cotato.tlinkserver.auth.dto;

import org.cotato.tlinkserver.auth.Token;

public record LoginResult(
        String accessToken,
        String refreshToken,
        boolean isOnboarding
) {
    public static LoginResult of(Token token, boolean isOnboarding) {
        return new LoginResult(token.accessToken(), token.refreshToken(), isOnboarding);
    }
}
