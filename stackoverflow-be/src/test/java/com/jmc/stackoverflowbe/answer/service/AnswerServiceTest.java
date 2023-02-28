package com.jmc.stackoverflowbe.answer.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import com.jmc.stackoverflowbe.answer.entity.Answer;
import com.jmc.stackoverflowbe.answer.entity.Answer.StateGroup;
import com.jmc.stackoverflowbe.answer.repository.AnswerRepository;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import com.jmc.stackoverflowbe.member.service.MemberService;
import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.service.QuestionService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class AnswerServiceTest {

    private final Member member = Member.builder()
        .memberId(1L)
        .email("hgd@gmail.com")
        .name("홍길동")
        .state(MemberState.ACTIVE)
        .build();

    private final Question question = Question.builder()
        .questionId(1L)
        .questionTitle("Question title for stub")
        .memberId(1L)
        .questionContent("Question contents for stub")
        .state(Question.StateGroup.ACTIVE)
        .votes(0)
        .selection(false)
        .answers(0L)
        .views(0L)
        .build();

    private final Answer answer = Answer.builder()
        .answerId(1L)
        .answerContent("testing content")
        .state(StateGroup.ACTIVE)
        .votes(0L)
        .member(member)
        .question(question)
        .build();

    private final Answer deletedAnswer = Answer.builder()
        .answerId(1L)
        .answerContent("testing content")
        .state(StateGroup.DELETED)
        .votes(0L)
        .member(member)
        .question(question)
        .build();

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private MemberService memberService;

    @Mock
    private QuestionService questionService;

    @Spy
    @InjectMocks
    private AnswerServiceImpl answerService;

    @DisplayName("Service단 답변 생성 로직")
    @Test
    public void createAnswerTest() {
//        given(memberService.findExistMemberById(Mockito.anyLong()))
//            .willReturn(new Member());
//        given(questionService.findExistQuestionById(Mockito.anyLong()))
//            .willReturn(new Question());
//        ReflectionTestUtils.invokeMethod(answerService, "verifyExistQuestionIdByEntity", answer);
        given(answerRepository.save(Mockito.any(Answer.class)))
            .willReturn(answer);

        Executable executable = () -> answerService.createAnswer(answer);

        assertDoesNotThrow(executable);
    }

    @DisplayName("Service단 답변 수정 로직")
    @Test
    public void updateAnswerTest() {
        given(answerRepository.findById(Mockito.anyLong()))
            .willReturn(Optional.of(answer));
        given(answerRepository.save(Mockito.any(Answer.class)))
            .willReturn(answer);

        Executable executable = () -> answerService.updateAnswer(answer, answer.getAnswerId());

        assertDoesNotThrow(executable);
    }

    @DisplayName("Service단 답변 리스트 조회 로직")
    @Test
    public void getAnswersTest() {
        ReflectionTestUtils.invokeMethod(answerService, "findExistAnswersByQuestionId",
            answer.getQuestion().getQuestionId());

        Executable executable = () -> answerService.getAnswers(answer.getAnswerId());

        assertDoesNotThrow(executable);
    }

    @DisplayName("Service단 답변 삭제 로직")
    @Test
    public void deleteAnswerTest() {
        given(answerRepository.findById(Mockito.anyLong()))
            .willReturn(Optional.of(new Answer()));
        given(answerRepository.save(Mockito.any(Answer.class)))
            .willReturn(deletedAnswer);

        Executable executable = () -> answerService.deleteAnswer(answer.getAnswerId());

        assertEquals(deletedAnswer.getState(), StateGroup.DELETED);
        assertDoesNotThrow(executable);
    }
}
