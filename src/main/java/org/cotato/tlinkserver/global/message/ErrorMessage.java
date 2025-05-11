package org.cotato.tlinkserver.global.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    /* 400 Bad Request */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON-001", "요청 형식이 올바르지 않습니다."),
    TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "COMMON-002", "올바르지 않은 쿼리 파라미터 형식입니다."),

    /* 401 Unauthorized */
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH-001", "로그인 검증을 실패했습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH-002", "토큰이 만료되었습니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH-003", "토큰이 올바르지 않습니다."),
    EMPTY_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH-004", "토큰이 전달되지 않았습니다."),

    /* 403 Forbidden*/
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON-003", "사용할 수 없는 권한이 없습니다."),

    /* 404 Not Found */
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON-004", "리소스가 존재하지 않습니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "USER-001", "사용자가 존재하지 않습니다."),
    NOT_FOUND_ROOM(HttpStatus.NOT_FOUND, "ROOM-001", "과외방이 존재하지 않습니다."),
    NOT_FOUND_SHARED_LINK(HttpStatus.NOT_FOUND, "ROOM-002", "공유 링크가 존재하지 않습니다."),
    NOT_FOUND_LECTURE_FILE_BOX(HttpStatus.NOT_FOUND, "LECT-001", "강의 자료함이 존재하지 않습니다."),
    NOT_FOUND_LECTURE_FILE(HttpStatus.NOT_FOUND, "LECT-002", "강의 자료 파일이 존재하지 않습니다."),
    NOT_FOUND_HOMEWORK(HttpStatus.NOT_FOUND, "HW-001", "숙제가 존재하지 않습니다."),
    NOT_FOUND_HOMEWORK_FILE(HttpStatus.NOT_FOUND, "HW-002", "숙제 파일이 존재하지 않습니다."),
    NOT_FOUND_COUNSELING_LOG(HttpStatus.NOT_FOUND, "CLOG-001", "상담 일지가 존재하지 않습니다."),
    NOT_FOUND_EXAM(HttpStatus.NOT_FOUND, "EXAM-001", "시험이 존재하지 않습니다."),
    NOT_FOUND_GRADE(HttpStatus.NOT_FOUND, "EXAM-002", "성적이 존재하지 않습니다."),
    NOT_FOUND_BANK(HttpStatus.NOT_FOUND, "BANK-001", "은행이 존재하지 않습니다."),
    NOT_FOUND_REGISTRATION(HttpStatus.NOT_FOUND, "REGI-001", "등록 정보가 존재하지 않습니다."),

    /* 405 Method Not Allowed */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON-005", "잘못된 HTTP 요청입니다."),

    /* 409 Conflict */
    CONFLICT(HttpStatus.CONFLICT, "COMMON-006", "이미 처리된 리소스입니다."),
    ALREADY_ENTERED(HttpStatus.CONFLICT, "ROOM-003", "현재 접속한 방입니다."),
    ALREADY_OCCUPIED(HttpStatus.CONFLICT, "ROOM-004", "다른 유저가 접속한 방입니다."),
    DUPLICATED_PHONE_NUMBER(HttpStatus.CONFLICT, "USER-002", "중복된 전화번호가 있습니다."),

    /* 500 Internal Server Error*/
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON-007", "서버 내부에서 오류가 발생했습니다."),
    KAKAO_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "AUTH-005", "카카오에서 유저 정보를 가져올 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
