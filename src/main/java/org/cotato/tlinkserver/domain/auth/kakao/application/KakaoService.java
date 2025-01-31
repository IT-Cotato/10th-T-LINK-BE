package org.cotato.tlinkserver.domain.auth.kakao.application;

import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cotato.tlinkserver.domain.auth.kakao.application.dto.response.KakaoTokenDTO;
import org.cotato.tlinkserver.domain.auth.kakao.application.dto.response.KakaoUserInfoDTO;
import org.cotato.tlinkserver.global.exception.TLinkException;
import org.cotato.tlinkserver.global.message.ErrorMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class KakaoService {

    @Value("${kakao.client_id}")
    private String clientId;

    private final String KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";
    private final String KAUTH_USER_URL_HOST = "https://kapi.kakao.com";

    public String getAccessTokenFromKakao(final String code) {

        KakaoTokenDTO kakaoToken = WebClient.create(KAUTH_TOKEN_URL_HOST).post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", clientId)
                        .queryParam("code", code)
                        .build(true)
                )
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new TLinkException(ErrorMessage.BAD_REQUEST))
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new TLinkException(ErrorMessage.INTERNAL_SERVER_ERROR))
                )
                .bodyToMono(KakaoTokenDTO.class)
                .block();

        return kakaoToken.getAccessToken();
    }

    public KakaoUserInfoDTO getUserInfo(String accessToken) {

        KakaoUserInfoDTO userInfo = WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken) // access token 인가
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                //TODO : Custom Exception
                .onStatus(
                        HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new TLinkException(ErrorMessage.BAD_REQUEST))
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new TLinkException(ErrorMessage.INTERNAL_SERVER_ERROR))
                )
                .bodyToMono(KakaoUserInfoDTO.class)
                .block();

        return userInfo;
    }
}
