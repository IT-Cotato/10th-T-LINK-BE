package org.cotato.tlinkserver.api.dto.response;

import org.cotato.tlinkserver.api.facade.dto.response.RoomInfoDetailLessonDayDTO;
import org.cotato.tlinkserver.domain.room.constant.DayOfWeek;

public record RoomInfoDetailLessonDayResponse(
        DayOfWeek lessonDay
) {
    public static RoomInfoDetailLessonDayResponse from(RoomInfoDetailLessonDayDTO roomInfoDetailLessonDayDTO) {
        return new RoomInfoDetailLessonDayResponse(
                roomInfoDetailLessonDayDTO.lessonDay()
        );
    }
}
