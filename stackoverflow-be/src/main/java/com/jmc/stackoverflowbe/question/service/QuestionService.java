package com.jmc.stackoverflowbe.question.service;

import com.jmc.stackoverflowbe.question.entity.Question;
import org.springframework.data.domain.Page;

public interface QuestionService {
    Question createQuestion(Question question);
    Question updateQuestion(Question question);
    Question getQuestion(Long id);
    Page<Question> getQuestions(int page, String sort);
    void deleteQuestion(Long id);
    Question findExistId(Long id);

}
