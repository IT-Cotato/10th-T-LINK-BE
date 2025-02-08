package org.cotato.tlinkserver.api.controller;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.annotation.Permission;
import org.cotato.tlinkserver.annotation.UserId;
import org.cotato.tlinkserver.api.dto.response.MyPageInfoResponse;
import org.cotato.tlinkserver.api.facade.UserFacade;
import org.cotato.tlinkserver.api.facade.dto.response.MyPageInfoDTO;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.cotato.tlinkserver.global.common.BaseResponse;
import org.cotato.tlinkserver.global.message.SuccessMessage;
import org.cotato.tlinkserver.global.util.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserFacade userFacade;

    @Permission(role = {Role.STUDENT, Role.PARENT, Role.TEACHER})
    @DeleteMapping
    public ResponseEntity<BaseResponse<?>> deleteAccount(@UserId Long userId) {
        userFacade.deleteAccount(userId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS);
    }

    @Permission(role = {Role.STUDENT, Role.PARENT, Role.TEACHER})
    @GetMapping("/mypage")
    public ResponseEntity<BaseResponse<?>> viewMyPage(
            @UserId Long userId
    ) {
        MyPageInfoDTO myPageInfoDTO = userFacade.getMyPageInfo(userId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, MyPageInfoResponse.from(myPageInfoDTO));
    }
}
