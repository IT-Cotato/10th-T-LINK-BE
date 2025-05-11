package org.cotato.tlinkserver.api.facade.dto.request;

import org.cotato.tlinkserver.domain.room.constant.DayOfWeek;

public record RoomLessonDayDTO
        (
                DayOfWeek lessonDay
        ) {
}
