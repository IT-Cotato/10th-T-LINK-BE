package org.cotato.tlinkserver.api.facade;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.annotation.Facade;
import org.cotato.tlinkserver.api.facade.dto.request.KakaoSignUpDTO;
import org.cotato.tlinkserver.domain.auth.kakao.application.KakaoService;
import org.cotato.tlinkserver.domain.auth.kakao.application.dto.response.KakaoUserInfoDTO;
import org.cotato.tlinkserver.domain.user.application.UserService;
import org.cotato.tlinkserver.global.exception.TLinkException;
import org.cotato.tlinkserver.global.message.ErrorMessage;
import org.springframework.transaction.annotation.Transactional;

@Facade
@RequiredArgsConstructor
public class AuthKakaoFacade {

    private final KakaoService kakaoService;
    private final UserService userService;

    @Transactional
    public void kakaoSignUp(final KakaoSignUpDTO kakaoSignUpDTO) {
        final String accessToken = kakaoService.getAccessTokenFromKakao(kakaoSignUpDTO.code());
        final KakaoUserInfoDTO userInfo = kakaoService.getUserInfo(accessToken);

        validateNotExistUser(userInfo.getId());

        userService.signUp(kakaoSignUpDTO, userInfo);
    }

    @Transactional(readOnly = true)
    protected void validateNotExistUser(final Long userId) {
        if (userService.existUserById(userId)) {
            throw new TLinkException(ErrorMessage.CONFLICT);
        }
    }
}
