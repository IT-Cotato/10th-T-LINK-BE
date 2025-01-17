package org.cotato.tlinkserver.api.dto.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponse<T> extends BaseResponse {

	private final String message;
	private final T data;

	private DataResponse(HttpStatus status, String message, T data) {
		super(status);
		this.message = message;
		this.data = data;
	}

	public static <T> DataResponse<T> of(final HttpStatus httpStatus, final String message, T data) {
		return new DataResponse<>(httpStatus, message, data);
	}

}
