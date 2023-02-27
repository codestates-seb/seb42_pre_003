package com.jmc.stackoverflowbe.question.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.BDDMockito.given;

import com.jmc.stackoverflowbe.question.dto.QuestionDto;
import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.entity.Question.StateGroup;
import com.jmc.stackoverflowbe.question.mapper.QuestionMapper;
import com.jmc.stackoverflowbe.question.repository.QuestionRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuestionMapper mapper;

    @InjectMocks
    private QuestionServiceImpl questionService;

    private final Question question = Question.builder()
        .questionId(0L)
        .questionContent("contents for service test")
        .memberId(0L)
        .questionTitle("service test")
        .views(0L)
        .votes(0)
        .state(StateGroup.ACTIVE)
        .selection(false)
        .answers(0L)
        .build();

    private final QuestionDto.Post post = QuestionDto.Post.builder()
        .questionTitle("title for service test post")
        .questionContent("contents for service test post")
        .build();

    private final QuestionDto.Patch patch = QuestionDto.Patch.builder()
        .questionTitle("title for service test patch")
        .questionTitle("contents for service test patch")
        .build();

    private final QuestionDto.Response response = QuestionDto.Response.builder()
        .questionId(0L)
        .questionTitle("title for service test response")
        .questionContent("content for service test response")
        .memberId(0L)
        .state(StateGroup.ACTIVE)
        .votes(0)
        .selection(false)
        .answers(0L)
        .views(0L)
        .build();

    private final QuestionDto.Response response2 = QuestionDto.Response.builder()
        .questionId(1L)
        .questionTitle("title for service test response")
        .questionContent("content for service test response")
        .memberId(1L)
        .state(StateGroup.ACTIVE)
        .votes(1)
        .selection(true)
        .answers(1L)
        .views(1L)
        .build();


    @DisplayName("질문 생성 service test")
    @Test
    public void createQuestionTest(){
        given(mapper.postDtoToQuestion(Mockito.any(QuestionDto.Post.class)))
            .willReturn(new Question());
        given(questionRepository.save(Mockito.any(Question.class)))
            .willReturn(question);

        Executable executable = () -> questionService.createQuestion(post);
        assertDoesNotThrow(executable);
    }

    @DisplayName("질문 수정 service test")
    @Test
    public void updateQuestionTest(){
        given(mapper.patchDtoToQuestion(Mockito.any(QuestionDto.Patch.class)))
            .willReturn(new Question());
        given(questionRepository.findById(Mockito.anyLong()))
            .willReturn(Optional.of(question));
        given(questionRepository.save(Mockito.any(Question.class)))
            .willReturn(question);

        Executable executable =
            () -> questionService.updateQuestion(patch, question.getQuestionId());

        assertDoesNotThrow(executable);
    }

    @DisplayName("질문 상세 조회 service test")
    @Test
    public void getQuestionTest(){
        given(questionRepository.findById(Mockito.anyLong()))
            .willReturn(Optional.of(question));
        given(mapper.questionToResponseDto(Mockito.any(Question.class)))
            .willReturn(response);

        Executable executable = () -> questionService.getQuestion(question.getQuestionId());

        assertDoesNotThrow(executable);
    }
    @DisplayName("질문 리스트 조회 service test")
    @Test
    public void getQuestionsTest(){

        Executable executable = () -> questionService.getQuestions(0, "questionId");
        assertDoesNotThrow(executable);
    }

    @DisplayName("질문 삭제 service test")
    @Test
    public void deleteQuestionTest(){
        given(questionRepository.findById(Mockito.anyLong()))
            .willReturn(Optional.of(question));
        Executable executable = () -> questionService.deleteQuestion(question.getQuestionId());

        assertDoesNotThrow(executable);
    }



}