package org.cotato.tlinkserver.domain.room.application.dto.response;

import org.cotato.tlinkserver.domain.room.Registration;

import lombok.Builder;

@Builder
public record PermissionResponse
	(
		boolean lectureFile,
		boolean homework,
		boolean gradeStatistic,
		boolean counselingLog,
		boolean deposit
	)
{
	public static PermissionResponse from(final Registration registration) {
		return PermissionResponse.builder()
			.lectureFile(registration.isLectureFile())
			.homework(registration.isHomework())
			.gradeStatistic(registration.isGradeStatistic())
			.counselingLog(registration.isCounselingLog())
			.deposit(registration.isDeposit())
			.build();
	}
}
