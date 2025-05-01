package org.cotato.tlinkserver.domain.room;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cotato.tlinkserver.domain.bank.Bank;
import org.cotato.tlinkserver.domain.counselingLog.CounselingLog;
import org.cotato.tlinkserver.domain.gradeStatistic.ExamBox;
import org.cotato.tlinkserver.domain.homework.Homework;
import org.cotato.tlinkserver.domain.lectureFile.LectureFileBox;
import org.cotato.tlinkserver.domain.parentPermission.ParentPermission;
import org.cotato.tlinkserver.domain.studentPermission.StudentPermission;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "rooms")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String studentName;

    @Column(nullable = false, length = 30)
    private String subject;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToOne
    @JoinColumn(name = "student_permission_id")
    private StudentPermission studentPermission;

    @OneToOne
    @JoinColumn(name = "parent_permission_id")
    private ParentPermission parentPermission;

    @Column(length = 20)
    private String accountNumber;

    @Column
    private int depositAt;

    @Column
    private int depositAmount;

    @Column(length = 250)
    private String shareCode;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureFileBox> lectureFileBoxes = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Homework> homeworks = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CounselingLog> counselingLogs = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registration> registrations = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LessonDay> lessonDays = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamBox> examBoxes = new ArrayList<>();

    @Builder
    public Room(String studentName, String subject, Bank bank,
                String accountNumber, int depositAt, int depositAmount, String shareCode,
                StudentPermission studentPermission, ParentPermission parentPermission) {
        this.studentName = studentName;
        this.subject = subject;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.depositAt = depositAt;
        this.depositAmount = depositAmount;
        this.shareCode = shareCode;
        this.studentPermission = studentPermission;
        this.parentPermission = parentPermission;
    }

    // 연관 관계 메서드
    public void addHomework(Homework homework) {
        homeworks.add(homework);
        homework.setRoom(this);
    }

    public void addLectureFileBox(LectureFileBox lectureFileBox) {
        lectureFileBoxes.add(lectureFileBox);
        lectureFileBox.setRoom(this);
    }

    public void addCounselingLog(CounselingLog counselingLog) {
        counselingLogs.add(counselingLog);
        counselingLog.setRoom(this);
    }

    public void addRegistration(Registration registration) {
        registrations.add(registration);
        registration.setRoom(this);
    }

    public void addLessonDay(LessonDay lessonDay) {
        lessonDays.add(lessonDay);
        lessonDay.setRoom(this);
    }

    public void addExamBox(ExamBox examBox) {
        examBoxes.add(examBox);
        examBox.setRoom(this);
    }


    public Optional<Registration> getRegistration(long userId) {
        return registrations.stream()
                .filter(registration -> registration.getUser() != null && registration.getUser().getId() == userId)
                .findFirst();
    }

    public Optional<String> getStudentUsername() {
        return registrations.stream()
                .filter(registration -> registration.getRole() == Role.STUDENT && registration.getUser() != null)
                .map(registration -> registration.getUser().getUsername())
                .findFirst();
    }
}
