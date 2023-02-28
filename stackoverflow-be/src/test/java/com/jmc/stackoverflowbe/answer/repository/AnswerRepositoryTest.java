package com.jmc.stackoverflowbe.answer.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jmc.stackoverflowbe.answer.entity.Answer;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import com.jmc.stackoverflowbe.member.repository.MemberRepository;
import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.entity.Question.StateGroup;
import com.jmc.stackoverflowbe.question.repository.QuestionRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class AnswerRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    private final Member member = Member.builder()
        .memberId(1L)
        .email("hgd@gmail.com")
        .name("홍길동")
        .state(MemberState.ACTIVE)
        .build();

    private final Question question = Question.builder()
        .questionId(1L)
        .questionTitle("Question title for stub")
        .member(member)
        .questionContent("Question contents for stub")
        .state(StateGroup.ACTIVE)
        .votes(0)
        .selection(false)
        .answers(0L)
        .views(0L)
        .build();

    private final Answer answer1 = Answer.builder()
        .answerId(1L)
        .answerContent("testing content")
        .state(Answer.StateGroup.ACTIVE)
        .votes(0L)
        .member(member)
        .question(question)
        .build();

    private final Answer answer2 = Answer.builder()
        .answerId(2L)
        .answerContent("testing content")
        .state(Answer.StateGroup.ACTIVE)
        .votes(0L)
        .member(member)
        .question(question)
        .build();

    @BeforeAll
    public void init() {
        memberRepository.save(member);
        questionRepository.save(question);
        answerRepository.save(answer1);
        answerRepository.save(answer2);
    }

    @Test
    public void findAllByQuestionQuestionIdTest() {
        List<Answer> answers = List.of(answer1, answer2);

        List<Answer> obtainedAnswers = answerRepository.findAllByQuestionQuestionId(1L);

        assertEquals(obtainedAnswers.get(0).getQuestion().getQuestionId(),
            answers.get(0).getQuestion().getQuestionId());
        assertEquals(obtainedAnswers.get(1).getQuestion().getQuestionId(),
            answers.get(1).getQuestion().getQuestionId());
        assertEquals(obtainedAnswers.get(0).getAnswerContent(),
            answers.get(0).getAnswerContent());
        assertEquals(obtainedAnswers.get(1).getAnswerContent(),
            answers.get(1).getAnswerContent());
        assertEquals(obtainedAnswers.get(0).getState(),
            answers.get(0).getState());
        assertEquals(obtainedAnswers.get(1).getState(),
            answers.get(1).getState());
    }
}
