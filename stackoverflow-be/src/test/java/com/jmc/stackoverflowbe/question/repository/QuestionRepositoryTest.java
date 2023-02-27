package com.jmc.stackoverflowbe.question.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import com.jmc.stackoverflowbe.member.repository.MemberRepository;
import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.entity.Question.StateGroup;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class  QuestionRepositoryTest {
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
        .questionId(0L)
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
        .questionId(1L)
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
    public void init(){
        memberRepository.save(member);
    }

    @Test
    public void findAllByMemberMemberIdTest(){


        List<Question> questions = List.of(question1,question2);
        questionRepository.saveAll(questions);
        List<Question> obtainQuestions =
            questionRepository.findAllByMemberMemberId(member.getMemberId());

        assertEquals(obtainQuestions.get(0).getMember().getMemberId(),
            questions.get(0).getMember().getMemberId());
        assertEquals(obtainQuestions.get(1).getMember().getMemberId(),
            questions.get(1).getMember().getMemberId());
    }

}
