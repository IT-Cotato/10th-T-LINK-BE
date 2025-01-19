package org.cotato.tlinkserver.api.facade;

import org.cotato.tlinkserver.domain.counselingLog.CounselingLog;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.CounselingLogService;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.request.CounselingLogSaveRequest;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.response.CounselingLogDetailResponse;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.response.CounselingLogsResponse;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.application.RoomService;
import org.cotato.tlinkserver.global.util.S3FileHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CounselingLogFacade {

	private final CounselingLogService counselingLogService;
	private final RoomService roomService;

	@Transactional(readOnly = true)
	public CounselingLogDetailResponse getCounselingLog(final Long counselingLogId) {
		return counselingLogService.getCounselingLog(counselingLogId);
	}

	@Transactional(readOnly = true)
	public CounselingLogsResponse getCounselingLogs(final Long roomId) {
		return counselingLogService.getCounselingLogs(roomId);
	}

	@Transactional
	public void saveCounselingLog(final Long roomId, final CounselingLogSaveRequest counselingLogSaveRequest) {
		CounselingLog counselingLog = CounselingLogSaveRequest.toCounselingLog(counselingLogSaveRequest);
		Room room = roomService.getRoom(roomId);
		room.addCounselingLog(counselingLog);
	}

	@Transactional
	public void modifyCounselingLog(final Long counselingLogId, final CounselingLogSaveRequest counselingLogSaveRequest) {
		counselingLogService.modifyCounselingLog(counselingLogId, counselingLogSaveRequest);
	}
}
