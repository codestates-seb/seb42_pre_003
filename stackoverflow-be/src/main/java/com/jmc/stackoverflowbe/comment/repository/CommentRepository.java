package com.jmc.stackoverflowbe.comment.repository;

import com.jmc.stackoverflowbe.comment.entity.Comment;
import com.jmc.stackoverflowbe.comment.entity.Comment.CommentState;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByQuestionQuestionIdAndCommentStateIs(Long qaId,
        CommentState commentState);

    List<Comment> findAllByAnswerAnswerIdAndCommentStateIs(Long qaId,
        CommentState commentState);
}
