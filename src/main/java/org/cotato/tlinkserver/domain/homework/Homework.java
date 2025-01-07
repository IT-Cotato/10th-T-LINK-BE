package org.cotato.tlinkserver.domain.homework;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.cotato.tlinkserver.domain.room.Room;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "homeworks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Homework {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "homework_id", updatable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "deadline", nullable = false)
	private LocalDateTime deadline;

	@Lob
	@Column(name = "discription", nullable = false, length = 300)
	private String description;

	@OneToMany(mappedBy = "homework", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HomeworkFile> homeworkFiles = new ArrayList<>();

	@Builder
	public Homework(Room room, LocalDateTime deadline, String description) {
		this.room = room;
		this.deadline = deadline;
		this.description = description;
	}

	// 연관 관계 메서드
	public void addHomeworkFile(HomeworkFile homeworkFile) {
		homeworkFiles.add(homeworkFile);
		homeworkFile.setHomework(this);
	}

}
