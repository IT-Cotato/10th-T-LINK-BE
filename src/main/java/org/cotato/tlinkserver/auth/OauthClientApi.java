package org.cotato.tlinkserver.auth;

import org.cotato.tlinkserver.auth.dto.OauthTokenResult;
import org.cotato.tlinkserver.auth.dto.SocialInfoResult;

public interface OauthClientApi {
    OauthTokenResult getAccessToken(String redirectUrl, String code);

    SocialInfoResult getSocialUserInfo(String accessToken);
}
