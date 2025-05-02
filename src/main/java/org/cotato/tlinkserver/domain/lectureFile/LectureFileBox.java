package org.cotato.tlinkserver.domain.lectureFile;

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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cotato.tlinkserver.domain.room.Room;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "lecture_file_boxes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class LectureFileBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(nullable = false, length = 50)
    private String name;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "lectureFileBox", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureFile> lectureFiles = new ArrayList<>();

    @Builder
    public LectureFileBox(Room room, String name) {
        this.room = room;
        this.name = name;
    }

    // 연관관계 메서드
    public void addLectureFile(LectureFile lectureFile) {
        lectureFiles.add(lectureFile);
        lectureFile.setLectureFileBox(this);
    }

}
