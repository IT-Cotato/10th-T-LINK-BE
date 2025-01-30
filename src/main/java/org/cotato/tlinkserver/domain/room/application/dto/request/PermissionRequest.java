package org.cotato.tlinkserver.domain.room.application.dto.request;

import org.cotato.tlinkserver.domain.room.Registration;

public record PermissionRequest
	(
		boolean lectureFile,
		boolean homework,
		boolean gradeStatistic,
		boolean counselingLog,
		boolean deposit
	)
{
	public void modify(Registration registration) {
		registration.setLectureFile(lectureFile);
		registration.setHomework(homework);
		registration.setGradeStatistic(gradeStatistic);
		registration.setCounselingLog(counselingLog);
		registration.setDeposit(deposit);
	}
}
