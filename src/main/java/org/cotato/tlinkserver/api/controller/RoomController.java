package org.cotato.tlinkserver.api.controller;

import org.cotato.tlinkserver.annotation.Permission;
import org.cotato.tlinkserver.annotation.UserId;
import org.cotato.tlinkserver.api.facade.RoomFacade;
import org.cotato.tlinkserver.domain.room.application.dto.request.RoomRequest;
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

import lombok.RequiredArgsConstructor;

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
	public ResponseEntity<BaseResponse<?>> getRoomModify(@PathVariable("roomId") Long roomId) {
		RoomModifyResponse roomModify = roomFacade.getRoomModify(roomId);
		return ApiResponseUtil.success(SuccessMessage.SUCCESS, roomModify);
	}

	@GetMapping("/{roomId}/shareCode")
	public ResponseEntity<BaseResponse<?>> getShareCode(@PathVariable("roomId") Long roomId) {
		ShareCodeResponse shareCode = roomFacade.getShareCode(roomId);
		return ApiResponseUtil.success(SuccessMessage.CREATED, shareCode);
	}

	@PostMapping
	public ResponseEntity<BaseResponse<?>> saveRoom(@RequestBody RoomRequest roomRequest) {
		Long userId = 1L; // 임시 teacher Id
		Long roomId = roomFacade.saveRoom(userId, roomRequest);
		return ApiResponseUtil.success(SuccessMessage.CREATED, roomId);
	}

	@PostMapping("/code/{shareCode}")
	public ResponseEntity<BaseResponse<?>> joinRoom(@PathVariable("shareCode") String shareCode) {
		Long userId = 1L; // 임시 student Id
		int result = roomFacade.joinRoom(userId, shareCode);

		return switch (result) {
			case 0, -1 -> ApiResponseUtil.failure(ErrorMessage.NOT_FOUND, result);
			default -> ApiResponseUtil.success(SuccessMessage.SUCCESS);
		};

	}

	@PatchMapping("/{roomId}")
	public ResponseEntity<BaseResponse<?>> modifyRoom(@PathVariable("roomId") Long roomId,
		@RequestBody RoomRequest roomRequest) {
		Long userId = 1L;
		roomFacade.modifyRoom(userId, roomId, roomRequest);
		return ApiResponseUtil.success(SuccessMessage.MODIFIED);
	}

	@DeleteMapping("/{roomId}")
	public ResponseEntity<BaseResponse<?>> removeRoom(@PathVariable("roomId") Long roomId) {
		Long userId = 1L;	// 임시 teacher Id
		roomFacade.deleteRoom(userId, roomId);
		return ApiResponseUtil.success(SuccessMessage.DELETED);
	}

}
