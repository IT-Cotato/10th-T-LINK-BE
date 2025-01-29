package org.cotato.tlinkserver.domain.user.application;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.api.facade.dto.request.KakaoSignUpDTO;
import org.cotato.tlinkserver.domain.auth.kakao.application.dto.response.KakaoUserInfoDTO;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.infra.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public boolean existUserById(final long userId) {
        return userRepository.existsById(userId);
    }

    @Transactional
    public void signUp(
            final KakaoSignUpDTO kakaoSignUpDTO,
            final KakaoUserInfoDTO userInfo
    ) {
        userRepository.save(
                User.builder()
                        .id(userInfo.getId())
                        .username(kakaoSignUpDTO.name())
                        .phoneNumber(kakaoSignUpDTO.phoneNumber())
                        .profilePath(userInfo.getKakaoAccount().getProfile().getProfileImageUrl())
                        .backgroundColor(kakaoSignUpDTO.backgroundColor())
                        .role(kakaoSignUpDTO.role())
                        .gender(kakaoSignUpDTO.gender())
                        .build()
        );
    }
}
