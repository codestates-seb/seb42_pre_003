package com.jmc.stackoverflowbe.comment.repository;

import com.jmc.stackoverflowbe.comment.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByQuestionQuestionId(Long qaId);

    List<Comment> findAllByAnswerAnswerId(Long qaId);
}
