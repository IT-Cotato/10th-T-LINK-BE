package org.cotato.tlinkserver.domain.gradeStatistic;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cotato.tlinkserver.domain.room.Room;

@Entity
@Table(name = "exam_boxes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class ExamBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @OneToMany(mappedBy = "examBox", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exam> exams = new ArrayList<>();

    @Builder
    public ExamBox(String name) {
        this.name = name;
    }

    public void addExam(Exam exam) {
        exams.add(exam);
        exam.setExamBox(this);
    }

}
