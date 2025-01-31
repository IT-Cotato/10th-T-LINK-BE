package org.cotato.tlinkserver.api.dto.response;

public record KakaoSignUpResponse (
        String accessToken
) {
    public static KakaoSignUpResponse from(final String accessToken) {
        return new KakaoSignUpResponse(
                accessToken
        );
    }
}
