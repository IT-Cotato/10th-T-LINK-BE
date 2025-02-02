package org.cotato.tlinkserver.domain.bank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "banks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bank_id", updatable = false)
	private Long id;

	@Column(name = "bank_name", nullable = false, length = 20)
	private String name;

	@Builder
	public Bank(String name) {
		this.name = name;
	}

}
