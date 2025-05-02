package org.cotato.tlinkserver.domain.homework.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cotato.tlinkserver.domain.homework.Homework;
import org.cotato.tlinkserver.domain.homework.infra.repository.HomeworkRepository;
import org.cotato.tlinkserver.global.exception.NotFoundException;
import org.cotato.tlinkserver.global.message.ErrorMessage;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeworkService {

    private final HomeworkRepository homeworkRepository;

    public Homework getHomework(final Long homeworkId) {
        return homeworkRepository.findById(homeworkId).orElseThrow(() -> new NotFoundException(
                ErrorMessage.NOT_FOUND_HOMEWORK));
    }

    public List<Homework> getHomeworks(final Long roomId) {
        return homeworkRepository.findHomeworksByRoomId(roomId);
    }

}
