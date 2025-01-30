package org.cotato.tlinkserver.domain.room.application.dto.response;

import org.cotato.tlinkserver.domain.room.LessonDay;

import lombok.Builder;

@Builder
public record LessonDayModifyResponse
	(
		Long lessonDayId,
		String lessonDay
	)
{
	public static LessonDayModifyResponse from(final LessonDay lessonDay) {
		return LessonDayModifyResponse.builder()
			.lessonDayId(lessonDay.getId())
			.lessonDay(lessonDay.getLessonDay().getInKorean())
			.build();
	}
}
