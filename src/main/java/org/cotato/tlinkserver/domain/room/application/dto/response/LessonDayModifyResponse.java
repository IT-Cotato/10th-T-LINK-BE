package org.cotato.tlinkserver.domain.room.application.dto.response;

import lombok.Builder;
import org.cotato.tlinkserver.domain.room.LessonDay;

@Builder
public record LessonDayModifyResponse
        (
                String lessonDay
        ) {
    public static LessonDayModifyResponse from(final LessonDay lessonDay) {
        return LessonDayModifyResponse.builder()
                .lessonDay(lessonDay.getLessonDay().getInKorean())
                .build();
    }
}
