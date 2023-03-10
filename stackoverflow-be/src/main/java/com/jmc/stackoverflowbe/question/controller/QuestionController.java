package com.jmc.stackoverflowbe.question.controller;

import com.jmc.stackoverflowbe.global.common.MultiResponseDto;
import com.jmc.stackoverflowbe.global.common.SingleResponseDto;
import com.jmc.stackoverflowbe.global.security.auth.dto.LogInMemberDto;
import com.jmc.stackoverflowbe.global.security.auth.resolver.LoginMember;
import com.jmc.stackoverflowbe.global.utils.UriCreator;
import com.jmc.stackoverflowbe.question.dto.QuestionDto;
import com.jmc.stackoverflowbe.question.dto.QuestionDto.Response;
import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.mapper.QuestionMapper;
import com.jmc.stackoverflowbe.question.service.QuestionService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper mapper;

    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post post,
        @LoginMember LogInMemberDto logInMemberDto) {
        Question question = questionService.createQuestion(mapper.postDtoToQuestion(post),
            logInMemberDto.getMemberId());
        URI location = UriCreator.createURI("/questions", question.getQuestionId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(
            @PathVariable("question-id") long questionId,
            @RequestBody QuestionDto.Patch patch,
        @LoginMember LogInMemberDto logInMemberDto) {
        Question question = mapper.patchDtoToQuestion(patch);
        question.setQuestionId(questionId);
        questionService.updateQuestion(question, logInMemberDto.getMemberId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") long questionId) {
        Question question = questionService.getQuestion(questionId);
        QuestionDto.Response response = mapper.questionToResponseDto(question);
        return new ResponseEntity(new SingleResponseDto<>(
                response),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getQuestions(
        @RequestParam String sort, @Positive @RequestParam int page) {
        Page<Question> questionPage = questionService.getQuestions(page - 1, sort);
        List<Question> questionList = questionPage.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionsToQuestionResponses(questionList),
                        questionPage),
                HttpStatus.OK);

    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") long questionId,
        @LoginMember LogInMemberDto logInMemberDto) {
        questionService.deleteQuestion(questionId, logInMemberDto.getMemberId());
        return ResponseEntity.noContent().build();
    }

}
