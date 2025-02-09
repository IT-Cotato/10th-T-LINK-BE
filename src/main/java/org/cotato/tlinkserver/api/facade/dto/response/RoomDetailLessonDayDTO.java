package org.cotato.tlinkserver.api.facade.dto.response;

import org.cotato.tlinkserver.domain.room.LessonDay;
import org.cotato.tlinkserver.domain.room.constant.DayOfWeek;

public record RoomDetailLessonDayDTO(
        DayOfWeek lessonDay
) {
    public static RoomDetailLessonDayDTO from(final LessonDay lessonDay) {
        return new RoomDetailLessonDayDTO(
                lessonDay.getLessonDay()
        );
    }
}
