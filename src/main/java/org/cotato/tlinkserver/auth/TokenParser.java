package org.cotato.tlinkserver.auth;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.global.exception.UnauthorizedException;
import org.cotato.tlinkserver.global.message.ErrorMessage;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenParser {

    private static final String BEARER_PREFIX = "Bearer ";

    public String getToken(String token) {
        if (token.startsWith(BEARER_PREFIX)) {
            return token.substring(BEARER_PREFIX.length());
        } else {
            throw new UnauthorizedException(ErrorMessage.INVALID_TOKEN);
        }
    }
}
