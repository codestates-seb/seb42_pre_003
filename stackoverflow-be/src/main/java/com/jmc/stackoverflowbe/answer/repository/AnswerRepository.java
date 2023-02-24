package com.jmc.stackoverflowbe.answer.repository;

import com.jmc.stackoverflowbe.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}