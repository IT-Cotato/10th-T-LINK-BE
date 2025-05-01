package org.cotato.tlinkserver.domain.counselingLog;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cotato.tlinkserver.domain.counselingLog.constant.Engagement;
import org.cotato.tlinkserver.domain.room.Room;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "counseling_logs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class CounselingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 300)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Engagement engagement;

    @Column
    private Boolean homeworkSubmitted;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public CounselingLog(Room room, String title, String content, Engagement engagement, Boolean homeworkSubmitted) {
        this.room = room;
        this.title = title;
        this.content = content;
        this.engagement = engagement;
        this.homeworkSubmitted = homeworkSubmitted;
    }

}
