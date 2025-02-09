package org.cotato.tlinkserver.api.dto.response;

public record RoomLessonDayResponse
	(
		String lessonDay
	)
{
	public static RoomLessonDayResponse from(String lessonDay) {
		return new RoomLessonDayResponse(lessonDay);
	}
}
