package org.cotato.tlinkserver.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OauthTokenResult(
        @JsonProperty("access_token")
        String accessToken
) {
}
