package com.jmc.stackoverflowbe.answer.controller;

import com.jmc.stackoverflowbe.answer.dto.AnswerDto;
import com.jmc.stackoverflowbe.answer.dto.AnswerDto.AnswerMultiResponseDto;
import com.jmc.stackoverflowbe.answer.entity.Answer;
import com.jmc.stackoverflowbe.answer.mapper.AnswerMapper;
import com.jmc.stackoverflowbe.answer.service.AnswerService;
import com.jmc.stackoverflowbe.global.utils.UriCreator;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper mapper;

    @PostMapping
    public ResponseEntity postAnswer(@RequestBody AnswerDto.Post post){
        answerService.createAnswer(mapper.postDtoToAnswer(post));
        URI location = UriCreator.createURI("/answers", 1L);
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(
        @PathVariable("answer-id") long answerId,
        @RequestBody AnswerDto.Patch patch) {
        answerService.updateAnswer(mapper.patchDtoToAnswer(patch));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAnswers(@RequestParam long questionId) {
        List<Answer> answers = answerService.getAnswers(questionId);

        return new ResponseEntity<>(new AnswerMultiResponseDto<>(
            mapper.answersToResponseDtos(answers)),
            HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") long answerId){
        answerService.deleteAnswer(answerId);
        return ResponseEntity.noContent().build();
    }
}
