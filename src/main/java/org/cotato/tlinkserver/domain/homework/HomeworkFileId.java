package org.cotato.tlinkserver.domain.homework;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@EqualsAndHashCode
public class HomeworkFileId implements Serializable {
    private String filePath;
    private Homework homework;
}
