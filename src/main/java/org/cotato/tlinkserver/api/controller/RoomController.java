package org.cotato.tlinkserver.api.controller;

import org.cotato.tlinkserver.api.facade.RoomFacade;
import org.cotato.tlinkserver.domain.room.application.dto.request.RoomRequest;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomModifyResponse;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomsResponse;
import org.cotato.tlinkserver.global.common.BaseResponse;
import org.cotato.tlinkserver.global.message.SuccessMessage;
import org.cotato.tlinkserver.global.util.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

	private final RoomFacade roomFacade;

	@GetMapping
	public ResponseEntity<BaseResponse<?>> getRooms() {
		Long userId = 1L;	// 임시
		RoomsResponse roomsAndOpponents = roomFacade.getRooms(userId);
		return ApiResponseUtil.success(SuccessMessage.SUCCESS, roomsAndOpponents);
	}

	@GetMapping("/{roomId}/info")
	public ResponseEntity<BaseResponse<?>> getRoomModify(@PathVariable("roomId") Long roomId) {
		Long userId = 1L;	// 임시 teacher Id
		RoomModifyResponse roomModify = roomFacade.getRoomModify(userId, roomId);
		return ApiResponseUtil.success(SuccessMessage.SUCCESS, roomModify);
	}

	@PatchMapping("/{roomId}")
	public ResponseEntity<BaseResponse<?>> modifyRoom(@PathVariable("roomId") Long roomId,
		@RequestBody RoomRequest roomRequest) {
		Long userId = 1L;
		roomFacade.modifyRoom(userId, roomId, roomRequest);
		return ApiResponseUtil.success(SuccessMessage.DELETED);
	}

	@DeleteMapping("/{roomId}")
	public ResponseEntity<BaseResponse<?>> removeRoom(@PathVariable("roomId") Long roomId) {
		Long userId = 1L;	// 임시 teacher Id
		roomFacade.deleteRoom(userId, roomId);
		return ApiResponseUtil.success(SuccessMessage.DELETED);
	}

}
