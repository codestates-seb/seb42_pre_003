package com.jmc.stackoverflowbe.answer.controller;

import com.jmc.stackoverflowbe.answer.dto.AnswerDto;
import com.jmc.stackoverflowbe.answer.dto.AnswerDto.AnswerMultiResponseDto;
import com.jmc.stackoverflowbe.answer.entity.Answer;
import com.jmc.stackoverflowbe.answer.mapper.AnswerMapper;
import com.jmc.stackoverflowbe.answer.service.AnswerService;
import com.jmc.stackoverflowbe.global.security.auth.dto.LogInMemberDto;
import com.jmc.stackoverflowbe.global.security.auth.resolver.LoginMember;
import com.jmc.stackoverflowbe.global.utils.UriCreator;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/answers")
@Validated
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    private final AnswerMapper mapper;

    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.Post post,
        @LoginMember LogInMemberDto loginMember) {
        Answer answer = answerService.createAnswer(mapper.postDtoToAnswer(post),
            loginMember.getMemberId());
        URI location = UriCreator.createURI("/answers", answer.getAnswerId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@Valid @RequestBody AnswerDto.Patch patch,
        @Positive @PathVariable("answer-id") long answerId,
        @LoginMember LogInMemberDto loginMember) {
        answerService.updateAnswer(mapper.patchDtoToAnswer(patch), answerId,
            loginMember.getMemberId());

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAnswers(@RequestParam long questionId) {
        List<Answer> answers = answerService.getAnswers(questionId);

        return new ResponseEntity<>(new AnswerMultiResponseDto<>(
            mapper.answersToResponseDtos(answers)), HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@Positive @PathVariable("answer-id") long answerId,
        @LoginMember LogInMemberDto loginMember) {
        answerService.deleteAnswer(answerId, loginMember.getMemberId());

        return ResponseEntity.noContent().build();
    }
}
