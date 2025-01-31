package org.cotato.tlinkserver.api.facade;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.annotation.Facade;
import org.cotato.tlinkserver.api.facade.dto.request.KakaoSignUpDTO;
import org.cotato.tlinkserver.auth.jwt.JwtTokenProvider;
import org.cotato.tlinkserver.auth.jwt.UserAuthentication;
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
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public String kakaoSignUp(final KakaoSignUpDTO kakaoSignUpDTO) {
        final String accessToken = kakaoService.getAccessTokenFromKakao(kakaoSignUpDTO.code());
        final KakaoUserInfoDTO userInfo = kakaoService.getUserInfo(accessToken);

        validateNotExistUser(userInfo.getId());

        final long userId = userService.signUp(kakaoSignUpDTO, userInfo);

        return generateToken(userId);
    }

    @Transactional(readOnly = true)
    protected void validateNotExistUser(final Long userId) {
        if (userService.existUserById(userId)) {
            throw new TLinkException(ErrorMessage.CONFLICT);
        }
    }

    private String generateToken(final long userId) {
        return jwtTokenProvider.generateToken(
                new UserAuthentication(userId, null, null)
        );
    }
}
