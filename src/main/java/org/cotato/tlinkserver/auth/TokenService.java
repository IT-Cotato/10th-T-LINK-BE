package org.cotato.tlinkserver.auth;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtTokenExtractor jwtTokenExtractor;
    private final TokenParser tokenParser;

    public Role getRole(String token) {
        return jwtTokenExtractor.getRole(tokenParser.getToken(token));
    }
}
