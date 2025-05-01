package org.cotato.tlinkserver.domain.homework.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cotato.tlinkserver.domain.homework.infra.repository.HomeworkFileRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeworkFileService {

    private final HomeworkFileRepository homeworkFileRepository;

}
