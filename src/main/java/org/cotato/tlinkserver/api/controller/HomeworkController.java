package org.cotato.tlinkserver.api.controller;

import org.cotato.tlinkserver.api.facade.HomeworkFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rooms/{roomId}/homeworks")
@RequiredArgsConstructor
public class HomeworkController {

	private final HomeworkFacade homeworkFacade;

}
