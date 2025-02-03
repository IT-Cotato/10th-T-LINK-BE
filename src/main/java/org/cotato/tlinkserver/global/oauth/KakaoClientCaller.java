package org.cotato.tlinkserver.global.oauth;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.auth.OauthClientApi;
import org.cotato.tlinkserver.auth.dto.OauthTokenResult;
import org.cotato.tlinkserver.auth.dto.SocialInfoResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class KakaoClientCaller implements OauthClientApi {

    @Value("${kakao.client_id}")
    private String clientId;
    private final RestClient restClient;

    @Override
    public OauthTokenResult getAccessToken(String redirectUrl, String code) {
        return restClient
                .method(HttpMethod.POST)
                .uri("https://kauth.kakao.com/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(createHttpBody(redirectUrl, code))
                .retrieve()
                .toEntity(OauthTokenResult.class)
                .getBody();
    }

    @Override
    public SocialInfoResult getSocialUserInfo(String accessToken) {
        return restClient
                .method(HttpMethod.GET)
                .uri("https://kapi.kakao.com/v2/user/me")
                .header("Authorization", createAuthorizationHeader(accessToken))
                .retrieve()
                .toEntity(SocialInfoResult.class)
                .getBody();
    }

    private String createHttpBody(String redirectUrl, String code) {
        return "grant_type=authorization_code" +
                "&client_id=" + clientId +
                "&redirect_uri=" + redirectUrl +
                "&code=" + code;
    }

    private String createAuthorizationHeader(String accessToken) {
        return "Bearer " + accessToken;
    }
}
