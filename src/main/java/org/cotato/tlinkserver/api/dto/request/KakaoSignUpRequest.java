package org.cotato.tlinkserver.api.dto.request;

public record KakaoSignUpRequest(
        String code,
        String role,
        String name,
        String phoneNumber,
        String gender
) {
}
