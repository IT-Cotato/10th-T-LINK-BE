package org.cotato.tlinkserver.domain.homework;

import org.cotato.tlinkserver.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity
@Table(name = "homework_files")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class HomeworkFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "homework_file_id", updatable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "homework_id")
	private Homework homework;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "original_name", nullable = false, length = 30)
	private String originalName;

	@Column(name = "file_path", length = 250)
	private String filePath;

	@Builder
	public HomeworkFile(String originalName, String filePath) {
		this.originalName = originalName;
		this.filePath = filePath;
	}
	
}
