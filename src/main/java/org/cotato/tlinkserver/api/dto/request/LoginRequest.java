package org.cotato.tlinkserver.api.dto.request;

import org.cotato.tlinkserver.domain.user.SocialProvider;

public record LoginRequest(
        SocialProvider provider,
        String redirectUrl,
        String code
) {
}
