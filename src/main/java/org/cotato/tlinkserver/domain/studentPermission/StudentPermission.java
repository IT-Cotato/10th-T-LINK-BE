package org.cotato.tlinkserver.domain.studentPermission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_permissions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class StudentPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean lectureFile;

    @Column(nullable = false)
    private boolean homework;

    @Column(nullable = false)
    private boolean gradeStatistic;

    @Column(nullable = false)
    private boolean counselingLog;

    @Column(nullable = false)
    private boolean deposit;

    public StudentPermission(boolean lectureFile, boolean homework, boolean gradeStatistic, boolean counselingLog,
                             boolean deposit) {
        this.lectureFile = lectureFile;
        this.homework = homework;
        this.gradeStatistic = gradeStatistic;
        this.counselingLog = counselingLog;
        this.deposit = deposit;
    }
}
