package org.cotato.tlinkserver.domain.counselingLog;

import java.time.LocalDateTime;

import org.cotato.tlinkserver.domain.room.Room;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "counseling_logs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class CounselingLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "counseling_log_id", updatable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;

	@Column(name = "title", nullable = false, length = 50)
	private String title;

	@Lob
	@Column(name = "content", nullable = false, length = 300)
	private String content;

	@Enumerated(EnumType.STRING)
	@Column(name = "engagement", nullable = false, length = 10)
	private Engagement engagement;

	@Column(name = "homework_submitted")
	private boolean homeworkSubmitted;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@Builder
	public CounselingLog(Room room, String title, String content, Engagement engagement, boolean homeworkSubmitted) {
		this.room = room;
		this.title = title;
		this.content = content;
		this.engagement = engagement;
		this.homeworkSubmitted = homeworkSubmitted;
		this.updatedAt = LocalDateTime.now();
	}

}
