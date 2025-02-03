package org.cotato.tlinkserver.api.dto.request;

import org.cotato.tlinkserver.domain.user.constant.Gender;
import org.cotato.tlinkserver.domain.user.constant.Role;

public record OnboardRequest(
        Role role,
        String username,
        String phoneNumber,
        Gender gender,
        String backgroundColor
) {
}
