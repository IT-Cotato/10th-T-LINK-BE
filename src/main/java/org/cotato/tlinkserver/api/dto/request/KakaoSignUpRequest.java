package org.cotato.tlinkserver.api.dto.request;

import org.cotato.tlinkserver.domain.user.constant.Gender;
import org.cotato.tlinkserver.domain.user.constant.Role;

public record KakaoSignUpRequest(
        String code,
        Role role,
        String name,
        String phoneNumber,
        Gender gender,
        String backgroundColor
) {
}
