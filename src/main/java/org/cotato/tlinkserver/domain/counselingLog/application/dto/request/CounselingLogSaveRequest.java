package org.cotato.tlinkserver.domain.counselingLog.application.dto.request;

import org.cotato.tlinkserver.domain.counselingLog.CounselingLog;
import org.cotato.tlinkserver.domain.counselingLog.constant.Engagement;

public record CounselingLogSaveRequest
	(
		String title,
		String content,
		String engagement,
		Boolean homeworkSubmitted
	)
{
	public static CounselingLog toCounselingLog(final CounselingLogSaveRequest counselingLogSaveRequest) {
		return CounselingLog.builder()
			.title(counselingLogSaveRequest.title)
			.content(counselingLogSaveRequest.content)
			.engagement(Engagement.valueOf(counselingLogSaveRequest.engagement))
			.homeworkSubmitted(counselingLogSaveRequest.homeworkSubmitted)
			.build();
	};
}
