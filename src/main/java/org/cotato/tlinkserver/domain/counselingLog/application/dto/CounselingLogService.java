package org.cotato.tlinkserver.domain.counselingLog.application.dto;

import java.util.List;

import org.cotato.tlinkserver.domain.counselingLog.CounselingLog;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.request.CounselingLogSaveRequest;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.response.CounselingLogDetailResponse;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.response.CounselingLogResponse;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.response.CounselingLogsResponse;
import org.cotato.tlinkserver.domain.counselingLog.constant.Engagement;
import org.cotato.tlinkserver.domain.counselingLog.infra.repository.CounselingLogRepository;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CounselingLogService {

	private final CounselingLogRepository counselingLogRepository;

	public CounselingLogDetailResponse getCounselingLog(final Long counselingLogId) {
		CounselingLog counselingLog = counselingLogRepository.findById(counselingLogId).orElseThrow();
		return CounselingLogDetailResponse.from(counselingLog);
	}

	public CounselingLogsResponse getCounselingLogs(final Long roomId) {
		List<CounselingLogResponse> counselingLogs = counselingLogRepository.findCounselingLogsByRoomId(roomId).stream()
			.map(CounselingLogResponse::from).toList();
		return CounselingLogsResponse.from(counselingLogs);
	}

	public void modifyCounselingLog(final Long counselingLogId, final CounselingLogSaveRequest counselingLogSaveRequest) {
		CounselingLog counselingLog = counselingLogRepository.findById(counselingLogId).orElseThrow();
		counselingLog.setTitle(counselingLogSaveRequest.title());
		counselingLog.setContent(counselingLogSaveRequest.content());
		counselingLog.setEngagement(Engagement.valueOf(counselingLogSaveRequest.engagement()));
		counselingLog.setHomeworkSubmitted(counselingLogSaveRequest.homeworkSubmitted());
	}

}
