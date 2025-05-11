package org.cotato.tlinkserver.api.dto.response;

import org.cotato.tlinkserver.api.facade.dto.response.RoomDetailLessonDayDTO;
import org.cotato.tlinkserver.domain.room.constant.DayOfWeek;

public record RoomDetailLessonDayResponse(
        DayOfWeek lessonDay
) {
    public static RoomDetailLessonDayResponse from(RoomDetailLessonDayDTO roomDetailLessonDayDTO) {
        return new RoomDetailLessonDayResponse(
                roomDetailLessonDayDTO.lessonDay()
        );
    }
}
