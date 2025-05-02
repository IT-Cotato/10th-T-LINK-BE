package org.cotato.tlinkserver.api.facade;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.annotation.Facade;
import org.cotato.tlinkserver.api.facade.dto.request.RewriteStatusMessageDTO;
import org.cotato.tlinkserver.api.facade.dto.response.MyPageInfoDTO;
import org.cotato.tlinkserver.api.facade.dto.response.UserProfileDTO;
import org.cotato.tlinkserver.auth.ReissueService;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.application.UserService;
import org.cotato.tlinkserver.global.exception.NotFoundException;
import org.cotato.tlinkserver.global.message.ErrorMessage;
import org.springframework.transaction.annotation.Transactional;

@Facade
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final ReissueService reissueService;

    @Transactional
    public void deleteAccount(long userId) {
        validateExistUser(userId);
        userService.deleteUserById(userId);
        reissueService.deleteAllByUserId(userId);
    }

    private void validateExistUser(long userId) {
        if (!userService.existUserById(userId)) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_USER);
        }
    }

    @Transactional(readOnly = true)
    public MyPageInfoDTO getMyPageInfo(long userId) {
        User user = userService.findById(userId);
        return MyPageInfoDTO.from(user);
    }

    @Transactional
    public void rewriteStatusMessage(long userId, RewriteStatusMessageDTO from) {
        User user = userService.getValidUser(userId);
        user.setStatusMessage(from.statusMessage());
    }

    @Transactional(readOnly = true)
    public UserProfileDTO getUserProfile(long userId) {
        return UserProfileDTO.from(
                userService.getValidUser(userId)
        );
    }
}
