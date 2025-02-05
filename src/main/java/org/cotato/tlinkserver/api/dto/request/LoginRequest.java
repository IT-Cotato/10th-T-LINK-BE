package org.cotato.tlinkserver.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.cotato.tlinkserver.domain.user.SocialProvider;

public record LoginRequest(
        @NotNull
        SocialProvider provider,
        @NotBlank
        String redirectUrl,
        @NotBlank
        String code
) {
}
