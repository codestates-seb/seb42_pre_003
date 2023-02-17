package com.jmc.stackoverflowbe.comment.repository;

import com.jmc.stackoverflowbe.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
