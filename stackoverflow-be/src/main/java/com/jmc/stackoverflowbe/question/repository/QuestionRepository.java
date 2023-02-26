package com.jmc.stackoverflowbe.question.repository;

import com.jmc.stackoverflowbe.question.entity.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByMemberId(Long memberId);

}
