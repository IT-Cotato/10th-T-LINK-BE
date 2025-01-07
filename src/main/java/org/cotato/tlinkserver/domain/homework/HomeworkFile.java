package org.cotato.tlinkserver.domain.homework;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "homework_files")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@IdClass(HomeworkFileId.class)
public class HomeworkFile {

	@Id
	@Column(name = "file_path", length = 250)
	private String filePath;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "homework_id")
	private Homework homework;

	@Builder
	public HomeworkFile(String filePath, Homework homework) {
		this.filePath = filePath;
		this.homework = homework;
	}

}
