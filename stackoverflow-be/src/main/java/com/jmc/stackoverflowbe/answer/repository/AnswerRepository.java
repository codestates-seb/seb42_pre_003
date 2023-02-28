package com.jmc.stackoverflowbe.answer.repository;

import com.jmc.stackoverflowbe.answer.entity.Answer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByQuestionQuestionId(Long questionId);
}