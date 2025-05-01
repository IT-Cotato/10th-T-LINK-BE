package org.cotato.tlinkserver.api.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.annotation.IdValidation;
import org.cotato.tlinkserver.annotation.Permission;
import org.cotato.tlinkserver.annotation.UserId;
import org.cotato.tlinkserver.api.dto.RoomJoinResponse;
import org.cotato.tlinkserver.api.dto.response.RoomDetailResponse;
import org.cotato.tlinkserver.api.dto.response.RoomInfoResponse;
import org.cotato.tlinkserver.api.facade.RoomFacade;
import org.cotato.tlinkserver.api.facade.dto.response.RoomDetailDTO;
import org.cotato.tlinkserver.api.facade.dto.response.RoomInfoDTO;
import org.cotato.tlinkserver.domain.room.application.dto.request.RoomModifyRequest;
import org.cotato.tlinkserver.domain.room.application.dto.request.RoomSaveRequest;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomModifyResponse;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomsResponse;
import org.cotato.tlinkserver.domain.room.application.dto.response.ShareCodeResponse;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.cotato.tlinkserver.global.common.BaseResponse;
import org.cotato.tlinkserver.global.message.ErrorMessage;
import org.cotato.tlinkserver.global.message.SuccessMessage;
import org.cotato.tlinkserver.global.util.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomFacade roomFacade;

    @Permission(role = {Role.STUDENT, Role.PARENT, Role.TEACHER})
    @GetMapping
    public ResponseEntity<BaseResponse<?>> getRooms(@UserId Long userId) {
        RoomsResponse roomsAndOpponents = roomFacade.getRooms(userId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, roomsAndOpponents);
    }

    @Permission(role = {Role.TEACHER})
    @GetMapping("/{roomId}/info")
    public ResponseEntity<BaseResponse<?>> getRoomModify(@PathVariable("roomId") @IdValidation Long roomId, @UserId Long userId) {
        RoomModifyResponse roomModify = roomFacade.getRoomModify(roomId, userId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, roomModify);
    }

    @Permission(role = {Role.TEACHER})
    @GetMapping("/{roomId}/shareCode")
    public ResponseEntity<BaseResponse<?>> getShareCode(@PathVariable("roomId") @IdValidation Long roomId) {
        ShareCodeResponse shareCode = roomFacade.getShareCode(roomId);
        return ApiResponseUtil.success(SuccessMessage.CREATED, shareCode);
    }

    @Permission(role = {Role.STUDENT, Role.PARENT})
    @GetMapping("/code/{shareCode}")
    public ResponseEntity<BaseResponse<?>> getInviter(@PathVariable("shareCode") String shareCode) {
        RoomJoinResponse inviter = roomFacade.getInviter(shareCode);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, inviter);
    }

    @Permission(role = {Role.TEACHER})
    @PostMapping
    public ResponseEntity<BaseResponse<?>> saveRoom(@UserId Long userId, @RequestBody RoomSaveRequest roomSaveRequest) {
        Long roomId = roomFacade.saveRoom(userId, roomSaveRequest);
        return ApiResponseUtil.success(SuccessMessage.CREATED, roomId);
    }

    @Permission(role = {Role.PARENT, Role.STUDENT})
    @PostMapping("/code/{shareCode}")
    public ResponseEntity<BaseResponse<?>> joinRoom(@UserId Long userId, @PathVariable("shareCode") String shareCode) {
        int result = roomFacade.joinRoom(userId, shareCode);
        return switch (result) {
            case 0, -1 -> ApiResponseUtil.failure(ErrorMessage.NOT_FOUND, result);
            default -> ApiResponseUtil.success(SuccessMessage.SUCCESS);
        };
    }

    @Permission(role = {Role.STUDENT, Role.PARENT, Role.TEACHER})
    @PatchMapping("/{roomId}")
    public ResponseEntity<BaseResponse<?>> modifyRoom(@UserId Long userId,
                                                      @PathVariable("roomId") @IdValidation Long roomId,
                                                      @RequestBody RoomModifyRequest roomModifyRequest) {
        roomFacade.modifyRoom(userId, roomId, roomModifyRequest);
        return ApiResponseUtil.success(SuccessMessage.MODIFIED);
    }

    @Permission(role = {Role.TEACHER})
    @DeleteMapping("/{roomId}")
    public ResponseEntity<BaseResponse<?>> removeRoom(@UserId Long userId,
                                                      @PathVariable("roomId") @IdValidation Long roomId) {
        roomFacade.removeRoom(userId, roomId);
        return ApiResponseUtil.success(SuccessMessage.DELETED);
    }

    @Permission(role = {Role.STUDENT, Role.PARENT, Role.TEACHER})
    @GetMapping("/info")
    public ResponseEntity<BaseResponse<?>> viewRoomInfo(
            @UserId Long userId
    ) {
        RoomInfoDTO roomInfoDTO = roomFacade.getRoomInfo(userId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, RoomInfoResponse.from(roomInfoDTO));
    }

    @Permission(role = {Role.STUDENT, Role.PARENT, Role.TEACHER})
    @GetMapping("/{roomId}")
    public ResponseEntity<BaseResponse<?>> viewRoomDetail(
            @UserId Long userId,
            @PathVariable @NotNull Long roomId
    ) {
        RoomDetailDTO roomDetailDTO = roomFacade.getRoomDetail(userId, roomId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, RoomDetailResponse.from(roomDetailDTO));
    }
}
