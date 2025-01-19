package org.cotato.tlinkserver.api.facade;

import java.util.List;

import org.cotato.tlinkserver.domain.counselingLog.application.dto.CounselingLogService;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.response.CounselingLogResponse;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.response.CounselingLogsResponse;
import org.cotato.tlinkserver.global.util.S3FileHandler;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CounselingLogFacade {

	private final CounselingLogService counselingLogService;
	private final S3FileHandler s3FileHandler;

	public CounselingLogsResponse getCounselingLogs(Long roomId) {
		return counselingLogService.getCounselingLogs(roomId);
	}
}
