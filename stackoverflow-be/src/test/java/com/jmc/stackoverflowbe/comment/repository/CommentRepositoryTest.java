package com.jmc.stackoverflowbe.comment.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jmc.stackoverflowbe.comment.entity.Comment;
import com.jmc.stackoverflowbe.comment.entity.Comment.CommentState;
import com.jmc.stackoverflowbe.comment.entity.Comment.CommentBuilder;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CommentRepositoryTest {

    private final CommentBuilder commentBuild1 = Comment.builder()
        .commentId(1L)
        .commentContent("Sample comment.")
        .memberId(1L)
        .memberName("kimcoding")
        .commentState(CommentState.ACTIVE);

    private final CommentBuilder commentBuild2 = Comment.builder()
        .commentId(2L)
        .commentContent("Sample comment 2.")
        .memberId(1L)
        .memberName("kimcoding")
        .commentState(CommentState.ACTIVE);

    @Autowired
    private CommentRepository commentRepository;

    // 연관 관계 매핑 후 findAllByQuestionId가 findAllByQuestionQuestionId로 변경 예정.
    @Test
    public void findAllByQuestionQuestionIdTest() {
        Comment comment1 = this.commentBuild1.questionId(1L).build();
        Comment comment2 = this.commentBuild2.questionId(1L).build();
        List<Comment> comments = List.of(comment1, comment2);

        commentRepository.save(comment1);
        commentRepository.save(comment2);
        List<Comment> obtainedComments = commentRepository.findAllByQuestionId(1L);

        assertEquals(obtainedComments.get(0).getCommentContent(),
            comments.get(0).getCommentContent());
        assertEquals(obtainedComments.get(1).getCommentContent(),
            comments.get(1).getCommentContent());
        assertEquals(obtainedComments.get(0).getMemberName(),
            comments.get(0).getMemberName());
        assertEquals(obtainedComments.get(1).getMemberName(),
            comments.get(1).getMemberName());
        assertEquals(obtainedComments.get(0).getCommentState(),
            comments.get(0).getCommentState());
        assertEquals(obtainedComments.get(1).getCommentState(),
            comments.get(1).getCommentState());
    }

    // 연관 관계 매핑 후 findAllByAnswerId가 findAllByAnswerAnswerId로 변경 예정.
    @Test
    public void findAllByAnswerAnswerIdTest() {
        Comment comment1 = this.commentBuild1.answerId(1L).build();
        Comment comment2 = this.commentBuild2.answerId(1L).build();
        List<Comment> comments = List.of(comment1, comment2);

        commentRepository.save(comment1);
        commentRepository.save(comment2);
        List<Comment> obtainedComments = commentRepository.findAllByAnswerId(1L);

        assertEquals(obtainedComments.get(0).getCommentContent(),
            comments.get(0).getCommentContent());
        assertEquals(obtainedComments.get(1).getCommentContent(),
            comments.get(1).getCommentContent());
        assertEquals(obtainedComments.get(0).getMemberName(),
            comments.get(0).getMemberName());
        assertEquals(obtainedComments.get(1).getMemberName(),
            comments.get(1).getMemberName());
        assertEquals(obtainedComments.get(0).getCommentState(),
            comments.get(0).getCommentState());
        assertEquals(obtainedComments.get(1).getCommentState(),
            comments.get(1).getCommentState());
    }

}
