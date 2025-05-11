package org.cotato.tlinkserver.domain.room.application;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cotato.tlinkserver.domain.parentPermission.ParentPermission;
import org.cotato.tlinkserver.domain.room.LessonDay;
import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.application.dto.request.RoomModifyRequest;
import org.cotato.tlinkserver.domain.room.application.dto.request.RoomSaveRequest;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomModifyResponse;
import org.cotato.tlinkserver.domain.room.infra.repository.RoomRepository;
import org.cotato.tlinkserver.domain.studentPermission.StudentPermission;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.cotato.tlinkserver.global.exception.NotFoundException;
import org.cotato.tlinkserver.global.message.ErrorMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomService {

    private final RoomRepository roomRepository;

    @Transactional(readOnly = true)
    public Room getRoom(final Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_ROOM));
    }

    @Transactional(readOnly = true)
    public Room getRoom(final String shareCode) {
        return roomRepository.findByShareCode(shareCode)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_ROOM));
    }

    @Transactional
    public Long saveRoom(final User teacher, final RoomSaveRequest request) {
        Room room = Room.builder()
                .studentName(request.studentName())
                .subject(request.subject())
                .build();

        request.lessonDays().forEach(day -> {
            LessonDay lessonDay = new LessonDay(day.lessonDay());
            room.addLessonDay(lessonDay);
        });

        Registration registration = new Registration(Role.TEACHER, request.roomName());
        teacher.addRegistration(registration);
        room.addRegistration(registration);
        room.setStudentPermission(request.studentPermission().toEntity());
        room.setParentPermission(request.parentPermission().toEntity());

        return roomRepository.save(room).getId();
    }

    @Transactional
    public void removeRoom(final Room room) {
        roomRepository.delete(room);
    }

    @Transactional(readOnly = true)
    public RoomModifyResponse getRoomModify(Long roomId, Long userId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_ROOM));
        return RoomModifyResponse.from(room, userId);
    }

    @Transactional
    public void modify(Room room, Registration registration, RoomModifyRequest request) {
        room.setStudentName(request.studentName());
        room.setSubject(request.subject());
        room.getLessonDays().clear();
        request.lessonDays().forEach(day -> {
            LessonDay lessonDay = new LessonDay(day.lessonDay());
            room.addLessonDay(lessonDay);
        });
        registration.setRoomName(request.roomName());
        modifyPermission(room.getParentPermission(), request);
        modifyPermission(room.getStudentPermission(), request);
    }

    @Transactional
    public void modify(Registration registration, RoomModifyRequest request) {
        registration.setRoomName(request.roomName());
    }

    private void modifyPermission(ParentPermission parentPermission, RoomModifyRequest request) {
        parentPermission.setCounselingLog(request.parentPermission().counselingLog());
        parentPermission.setDeposit(request.parentPermission().deposit());
        parentPermission.setGradeStatistic(request.parentPermission().gradeStatistic());
        parentPermission.setHomework(request.parentPermission().homework());
        parentPermission.setLectureFile(request.parentPermission().lectureFile());
    }

    private void modifyPermission(StudentPermission studentPermission, RoomModifyRequest request) {
        studentPermission.setCounselingLog(request.parentPermission().counselingLog());
        studentPermission.setDeposit(request.parentPermission().deposit());
        studentPermission.setGradeStatistic(request.parentPermission().gradeStatistic());
        studentPermission.setHomework(request.parentPermission().homework());
        studentPermission.setLectureFile(request.parentPermission().lectureFile());
    }
}
