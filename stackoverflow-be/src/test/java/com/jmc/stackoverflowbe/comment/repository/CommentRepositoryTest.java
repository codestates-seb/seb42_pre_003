package com.jmc.stackoverflowbe.comment.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jmc.stackoverflowbe.answer.entity.Answer;
import com.jmc.stackoverflowbe.answer.repository.AnswerRepository;
import com.jmc.stackoverflowbe.comment.entity.Comment;
import com.jmc.stackoverflowbe.comment.entity.Comment.CommentState;
import com.jmc.stackoverflowbe.comment.entity.Comment.CommentBuilder;
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
public class CommentRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    Member member = Member.builder()
        .memberId(1L)
        .email("hgd@gmail.com")
        .name("홍길동")
        .state(MemberState.ACTIVE)
        .build();

    Question question = Question.builder()
        .questionId(1L)
        .questionTitle("Question title for stub")
        .memberId(1L)
        .questionContent("Question contents for stub")
        .state(StateGroup.ACTIVE)
        .votes(0)
        .selection(false)
        .answers(0L)
        .views(0L)
        .build();

    Answer answer = Answer.builder()
        .answerId(1L)
        .answerContent("Test content.")
        .state(Answer.StateGroup.ACTIVE)
        .votes(0L)
        .member(member)
        .question(question)
        .build();

    private final CommentBuilder commentBuild1 = Comment.builder()
        .commentId(1L)
        .commentContent("Sample comment.")
        .member(member)
        .commentState(CommentState.ACTIVE);

    private final CommentBuilder commentBuild2 = Comment.builder()
        .commentId(2L)
        .commentContent("Sample comment 2.")
        .member(member)
        .commentState(CommentState.ACTIVE);

    @Autowired
    private CommentRepository commentRepository;

    @BeforeAll
    public void init() {
        memberRepository.save(member);
        questionRepository.save(question);
        answerRepository.save(answer);
    }

    @Test
    public void findAllByQuestionQuestionIdTest() {
        Comment comment1 = this.commentBuild1
            .question(question).build();
        Comment comment2 = this.commentBuild2
            .question(question).build();
        List<Comment> comments = List.of(comment1, comment2);

        commentRepository.save(comment1);
        commentRepository.save(comment2);
        List<Comment> obtainedComments = commentRepository.findAllByQuestionQuestionId(1L);

        assertEquals(obtainedComments.get(0).getCommentContent(),
            comments.get(0).getCommentContent());
        assertEquals(obtainedComments.get(1).getCommentContent(),
            comments.get(1).getCommentContent());
        assertEquals(obtainedComments.get(0).getMember().getName(),
            comments.get(0).getMember().getName());
        assertEquals(obtainedComments.get(1).getMember().getName(),
            comments.get(1).getMember().getName());
        assertEquals(obtainedComments.get(0).getCommentState(),
            comments.get(0).getCommentState());
        assertEquals(obtainedComments.get(1).getCommentState(),
            comments.get(1).getCommentState());
    }

    @Test
    public void findAllByAnswerAnswerIdTest() {
        Comment comment1 = this.commentBuild1
            .answer(answer).build();
        Comment comment2 = this.commentBuild2
            .answer(answer).build();
        List<Comment> comments = List.of(comment1, comment2);

        commentRepository.save(comment1);
        commentRepository.save(comment2);
        List<Comment> obtainedComments = commentRepository.findAllByAnswerAnswerId(1L);

        assertEquals(obtainedComments.get(0).getCommentContent(),
            comments.get(0).getCommentContent());
        assertEquals(obtainedComments.get(1).getCommentContent(),
            comments.get(1).getCommentContent());
        assertEquals(obtainedComments.get(0).getMember().getName(),
            comments.get(0).getMember().getName());
        assertEquals(obtainedComments.get(1).getMember().getName(),
            comments.get(1).getMember().getName());
        assertEquals(obtainedComments.get(0).getCommentState(),
            comments.get(0).getCommentState());
        assertEquals(obtainedComments.get(1).getCommentState(),
            comments.get(1).getCommentState());
    }

}
