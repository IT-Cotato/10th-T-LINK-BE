package org.cotato.tlinkserver.api.facade;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.annotation.Facade;
import org.cotato.tlinkserver.api.dto.RoomJoinResponse;
import org.cotato.tlinkserver.api.facade.dto.response.RoomDetailDTO;
import org.cotato.tlinkserver.api.facade.dto.response.RoomInfoDTO;
import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.application.RegistrationService;
import org.cotato.tlinkserver.domain.room.application.RoomService;
import org.cotato.tlinkserver.domain.room.application.dto.request.RoomModifyRequest;
import org.cotato.tlinkserver.domain.room.application.dto.request.RoomSaveRequest;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomModifyResponse;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomResponse;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomsResponse;
import org.cotato.tlinkserver.domain.room.application.dto.response.ShareCodeResponse;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.application.UserService;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.cotato.tlinkserver.global.exception.NotFoundException;
import org.cotato.tlinkserver.global.exception.UnauthorizedException;
import org.cotato.tlinkserver.global.message.ErrorMessage;
import org.cotato.tlinkserver.global.util.RandomUtil;
import org.springframework.transaction.annotation.Transactional;

@Facade
@RequiredArgsConstructor
public class RoomFacade {

    private final RoomService roomService;
    private final RegistrationService registrationService;
    private final UserService userService;

    @Transactional
    public Long saveRoom(final Long teacherId, final RoomSaveRequest request) {
        User teacher = userService.getValidUser(teacherId);
        return roomService.saveRoom(teacher, request);
    }

    @Transactional(readOnly = true)
    public RoomsResponse getRooms(final Long userId) {
        User user = userService.getValidUser(userId);
        List<Room> userRooms = registrationService.getRegistrations(userId).stream().map(Registration::getRoom)
                .toList();

        if (user.getRole().equals(Role.TEACHER)) {
            return RoomsResponse.from(userRooms.stream()
                    .map(room -> {
                        String roomName = registrationService.getRegistration(userId, room.getId()).getRoomName();
                        Registration studentRegistration = registrationService.getRegistration(room.getId(),
                                Role.STUDENT);
                        return RoomResponse.from(room, roomName, studentRegistration, room.getStudentName());
                    })
                    .toList());
        } else if (user.getRole().equals(Role.STUDENT) || user.getRole().equals(Role.PARENT)) {
            return RoomsResponse.from(userRooms.stream()
                    .map(room -> {
                        String roomName = registrationService.getRegistration(userId, room.getId()).getRoomName();
                        Registration teacherRegistration = registrationService.getRegistration(room.getId(),
                                Role.TEACHER);
                        return RoomResponse.from(room, roomName, teacherRegistration);
                    })
                    .toList());
        } else {
            throw new UnauthorizedException(ErrorMessage.UNAUTHORIZED);
        }
    }

    @Transactional(readOnly = true)
    public RoomModifyResponse getRoomModify(final Long roomId, final Long userId) {
        return roomService.getRoomModify(roomId, userId);
    }

    @Transactional
    public void modifyRoom(final Long userId, final Long roomId, final RoomModifyRequest request) {
        User user = userService.getValidUser(userId);
        Room room = roomService.getRoom(roomId);
        Registration registration = registrationService.getRegistration(userId, roomId);

        if (user.getRole().equals(Role.TEACHER)) {
            roomService.modify(room, registration, request);

        } else if (user.getRole().equals(Role.STUDENT) || user.getRole().equals(Role.PARENT)) {
            roomService.modify(registration, request);
        }
    }

    @Transactional
    public void removeRoom(final Long userId, final Long roomId) {
        Registration registration = registrationService.getRegistration(userId, roomId);
        Room room = registration.getRoom();
        roomService.removeRoom(room);
    }

    @Transactional
    public ShareCodeResponse getShareCode(final Long roomId) {
        String shareCode = RandomUtil.generateRandomCode('0', 'z', 10);
        Room room = roomService.getRoom(roomId);
        room.setShareCode(shareCode);
        return ShareCodeResponse.from(shareCode);
    }

    @Transactional
    public int joinRoom(final Long userId, final String shareCode) {
        User user = userService.getValidUser(userId);
        Room room = roomService.getRoom(shareCode);

        Registration registration = registrationService.getRegistration(room.getId(), user.getRole());

        if (registration.getUser() == null) {
            registration.setUser(user);
            user.addRegistration(registration);
            return 1;
        } else if (registration.getUser().equals(user)) {
            return 0;
        } else {
            return -1;
        }
    }

    @Transactional(readOnly = true)
    public RoomInfoDTO getRoomInfo(long userId) {
        List<Registration> registrations = registrationService.getRegistrationsWithRoomInfo(userId);
        return RoomInfoDTO.from(registrations);
    }

    @Transactional(readOnly = true)
    public RoomDetailDTO getRoomDetail(long userId, long roomId) {
        Room room = roomService.getRoom(roomId);
        Registration userRegistration = getValidRegistration(room, userId);
        String studentUsername = getStudentUsername(room);
        return RoomDetailDTO.of(userRegistration, studentUsername);
    }

    private Registration getValidRegistration(final Room room, final long userId) {
        return room.getRegistration(userId)
                .orElseThrow(
                        () -> new NotFoundException(ErrorMessage.NOT_FOUND_REGISTRATION)
                );
    }

    private String getStudentUsername(final Room room) {
        return room.getStudentUsername().orElse(null);
    }

    @Transactional(readOnly = true)
    public RoomJoinResponse getInviter(String shareCode) {
        Room room = roomService.getRoom(shareCode);
        Registration registration = registrationService.getRegistration(room.getId(), Role.TEACHER);
        return RoomJoinResponse.from(registration);
    }
}
