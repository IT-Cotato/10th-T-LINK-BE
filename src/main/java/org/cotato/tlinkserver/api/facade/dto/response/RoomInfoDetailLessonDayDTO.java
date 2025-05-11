package org.cotato.tlinkserver.api.facade.dto.response;

import org.cotato.tlinkserver.domain.room.LessonDay;
import org.cotato.tlinkserver.domain.room.constant.DayOfWeek;

public record RoomInfoDetailLessonDayDTO(
        DayOfWeek lessonDay
) {
    public static RoomInfoDetailLessonDayDTO from(final LessonDay lessonDay) {
        return new RoomInfoDetailLessonDayDTO(
                lessonDay.getLessonDay()
        );
    }
}
