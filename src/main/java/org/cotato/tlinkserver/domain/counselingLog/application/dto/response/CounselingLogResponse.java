package org.cotato.tlinkserver.domain.counselingLog.application.dto.response;

import org.cotato.tlinkserver.domain.counselingLog.CounselingLog;

public record CounselingLogResponse
	(
		Long id,
		String title,
		String updatedAt
	)
{
	public static CounselingLogResponse from(CounselingLog counselingLog) {
		return new CounselingLogResponse(
			counselingLog.getId(),
			counselingLog.getTitle(),
			counselingLog.getUpdatedAt().toLocalDate().toString()
		);
	}
}
