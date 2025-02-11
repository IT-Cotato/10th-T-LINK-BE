package org.cotato.tlinkserver.api.dto;

public record RoomJoinResponse
	(
		String teacherName
	)
{
	public static RoomJoinResponse from(String teacherName) {
		return new RoomJoinResponse(teacherName);
	}
}
