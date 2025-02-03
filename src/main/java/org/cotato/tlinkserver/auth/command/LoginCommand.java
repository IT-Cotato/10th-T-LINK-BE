package org.cotato.tlinkserver.auth.command;

import org.cotato.tlinkserver.domain.user.SocialProvider;

public record LoginCommand(
        SocialProvider provider,
        String redirectUrl,
        String code
) {
}
