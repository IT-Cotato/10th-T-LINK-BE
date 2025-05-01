package org.cotato.tlinkserver.domain.parentPermission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parent_permissions")
@Getter
@Setter
public class ParentPermission {

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
}
