package org.cotato.tlinkserver.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.cotato.tlinkserver.domain.user.constant.Gender;
import org.cotato.tlinkserver.domain.user.constant.Role;

public record OnboardRequest(
        @NotNull
        Role role,
        @NotBlank
        String username,
        @NotBlank
        String phoneNumber,
        @NotNull
        Gender gender,
        @NotBlank
        String backgroundColor
) {
}
