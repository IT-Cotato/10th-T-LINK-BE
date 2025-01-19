package org.cotato.tlinkserver.domain.counselingLog.application.dto.response;

import java.util.List;

public record CounselingLogsResponse
	(
		List<CounselingLogResponse> counselingLogs
	)
{
	public static CounselingLogsResponse from(final List<CounselingLogResponse> counselingLogs) {
		return new CounselingLogsResponse(counselingLogs);
	}
}
