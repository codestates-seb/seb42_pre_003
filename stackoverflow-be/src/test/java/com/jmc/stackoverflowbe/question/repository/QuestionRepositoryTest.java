package com.jmc.stackoverflowbe.question.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import com.jmc.stackoverflowbe.member.repository.MemberRepository;
import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.entity.Question.StateGroup;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class QuestionRepositoryTest {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    MemberRepository memberRepository;

    Member member = Member.builder()
        .memberId(1L)
        .email("hgd@gmail.com")
        .name("홍길동")
        .state(MemberState.ACTIVE)
        .build();

    Question question1 = Question.builder()
        .questionId(1L)
        .questionTitle("Question1 title for test")
        .member(member)
        .questionContent("Question1 contents for test")
        .state(StateGroup.ACTIVE)
        .votes(0)
        .selection(false)
        .answers(0L)
        .views(0L)
        .build();
    Question question2 = Question.builder()
        .questionId(2L)
        .questionTitle("Question2 title for test")
        .member(member)
        .questionContent("Question2 contents for test")
        .state(StateGroup.ACTIVE)
        .votes(1)
        .selection(false)
        .answers(1L)
        .views(1L)
        .build();

    @BeforeAll
    public void init() {
        memberRepository.save(member);
        questionRepository.save(question1);
    }

    @DisplayName("질문 저장")
    @Test
    public void saveQuestionTest() {
        Question savedQuestion = questionRepository.save(question2);

        assertNotNull(savedQuestion);
        assertTrue(question2.getQuestionId()
            .equals(savedQuestion.getQuestionId()));    // savedMember와 member3의 필드가 동일한지 검증.
        assertTrue(question2.getQuestionTitle().equals(savedQuestion.getQuestionTitle()));
        assertTrue(question2.getQuestionContent().equals(savedQuestion.getQuestionContent()));
    }

    @DisplayName("작성자로 질문 찾기")
    @Test
    public void findAllByMemberMemberIdTest() {
        List<Question> questions = List.of(question1, question2);
        questionRepository.saveAll(questions);
        List<Question> obtainQuestions =
            questionRepository.findAllByMemberMemberId(member.getMemberId());

        assertEquals(obtainQuestions.get(0).getMember().getMemberId(),
            questions.get(0).getMember().getMemberId());
        assertEquals(obtainQuestions.get(1).getMember().getMemberId(),
            questions.get(1).getMember().getMemberId());
    }

}
