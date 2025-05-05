package org.cotato.tlinkserver.domain.room;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.constant.Role;

@Entity
@Table(name = "registrations")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;

    @Column(nullable = false, length = 50)
    private String roomName;

    @Builder
    public Registration(Role role, String roomName) {
        this.role = role;
        this.roomName = roomName;
    }

    public boolean getLectureFilePermission() {
        if (role == Role.STUDENT) {
            return room.getStudentPermission().isLectureFile();
        }

        if (role == Role.PARENT) {
            return room.getStudentPermission().isLectureFile();
        }

        return true;
    }

    public boolean getHomeworkPermission() {
        if (role == Role.STUDENT) {
            return room.getStudentPermission().isHomework();
        }

        if (role == Role.PARENT) {
            return room.getStudentPermission().isHomework();
        }

        return true;
    }

    public boolean getGradeStatisticPermission() {
        if (role == Role.STUDENT) {
            return room.getStudentPermission().isGradeStatistic();
        }

        if (role == Role.PARENT) {
            return room.getStudentPermission().isGradeStatistic();
        }

        return true;
    }

    public boolean getCounselingLogPermission() {
        if (role == Role.STUDENT) {
            return room.getStudentPermission().isCounselingLog();
        }

        if (role == Role.PARENT) {
            return room.getStudentPermission().isCounselingLog();
        }

        return true;
    }

    public boolean getDepositPermission() {
        if (role == Role.STUDENT) {
            return room.getStudentPermission().isDeposit();
        }

        if (role == Role.PARENT) {
            return room.getStudentPermission().isDeposit();
        }

        return true;
    }
}
