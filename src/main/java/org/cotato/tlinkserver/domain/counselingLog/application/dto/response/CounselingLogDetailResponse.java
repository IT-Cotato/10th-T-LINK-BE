package org.cotato.tlinkserver.domain.counselingLog.application.dto.response;

import org.cotato.tlinkserver.domain.counselingLog.CounselingLog;

public record CounselingLogDetailResponse
	(
		Long counselingLogId,
		String title,
		String content,
		String engagement,
		Boolean homeworkSubmitted,
		String updatedAt
	)
{
	public static CounselingLogDetailResponse from(CounselingLog counselingLog) {
		return new CounselingLogDetailResponse(
			counselingLog.getId(),
			counselingLog.getTitle(),
			counselingLog.getContent(),
			counselingLog.getEngagement().getInKorean(),
			counselingLog.getHomeworkSubmitted(),
			counselingLog.getUpdatedAt().toLocalDate().toString()
		);
	}
}
