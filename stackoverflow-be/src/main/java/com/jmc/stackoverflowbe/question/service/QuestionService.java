package com.jmc.stackoverflowbe.question.service;

import com.jmc.stackoverflowbe.question.entity.Question;

public interface QuestionService {
    Question createQuestion(Question question);
    Question updateQuestion(Question question);
    Question getQuestion(Long id);
    void deleteQuestion(Long id);
    Question findExistId(Long id);

}
