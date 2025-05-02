package org.cotato.tlinkserver.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cotato.tlinkserver.auth.command.LoginCommand;
import org.cotato.tlinkserver.auth.dto.LoginResult;
import org.cotato.tlinkserver.auth.dto.OauthTokenResult;
import org.cotato.tlinkserver.auth.dto.SocialInfoResult;
import org.cotato.tlinkserver.domain.token.RefreshToken;
import org.cotato.tlinkserver.domain.token.infra.RefreshTokenRepository;
import org.cotato.tlinkserver.domain.user.AuthUser;
import org.cotato.tlinkserver.domain.user.SocialProvider;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.infra.repository.UserRepository;
import org.cotato.tlinkserver.global.exception.TLinkException;
import org.cotato.tlinkserver.global.message.ErrorMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final OauthClientApi oauthClientApi;
    private final UserRepository userRepository;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public AuthUser getAuthUser(LoginCommand command) {
        SocialInfoResult socialInfoResult = getSocialInfo(command);
        AuthUser retrievedAuthUser = userRepository.findBySocialIdAndProvider(
                        socialInfoResult.id(),
                        command.provider()
                )
                .map(AuthUser::toAuthUser)
                .orElse(null);

        if (retrievedAuthUser != null) {
            return retrievedAuthUser;
        }

        AuthUser createAuthUser = AuthUser.createWithId(
                socialInfoResult.id(),
                command.provider(),
                socialInfoResult.kakaoAccount().profile().profileImageUrl()
        );

        createAuthUser.setId(userRepository.save(
                User.create(createAuthUser)
        ).getId());

        return createAuthUser;
    }

    @Transactional
    public Token getToken(AuthUser authUser) {
        Token token = createToken(authUser);
        refreshTokenRepository.save(
                RefreshToken.of(token.refreshToken(), authUser.getId())
        );
        return token;
    }

    @Transactional(readOnly = true)
    public LoginResult getLoginResult(Token token, String socialId, SocialProvider provider) {
        User user = userRepository.findBySocialIdAndProvider(socialId, provider)
                .orElseThrow(
                        () -> new TLinkException(ErrorMessage.NOT_FOUND_USER)
                );

        return LoginResult.of(token, user.isOnboarding());
    }

    private Token createToken(AuthUser authUser) {
        return new Token(
                jwtTokenGenerator.createAccessToken(String.valueOf(authUser.getSocialId()), authUser.getRole()),
                jwtTokenGenerator.createRefreshToken(String.valueOf(authUser.getSocialId()), authUser.getRole())
        );
    }

    private SocialInfoResult getSocialInfo(LoginCommand command) {
        OauthTokenResult tokenResult = oauthClientApi.getAccessToken(command.redirectUrl(), command.code());
        return oauthClientApi.getSocialUserInfo(tokenResult.accessToken());
    }
}
