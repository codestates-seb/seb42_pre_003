package com.jmc.stackoverflowbe.question.service;

import com.jmc.stackoverflowbe.question.dto.QuestionDto;
import com.jmc.stackoverflowbe.question.dto.QuestionDto.Patch;
import com.jmc.stackoverflowbe.question.dto.QuestionDto.Response;
import com.jmc.stackoverflowbe.question.entity.Question;
import org.springframework.data.domain.Page;

public interface QuestionService {
    Question createQuestion(Question question, Long memberId);
    Question updateQuestion(Question question, Long memberId);
    Question getQuestion(Long id);
    Page<Question> getQuestions(int page, String sort);
    void deleteQuestion(Long id, Long memberId);
    Question findExistQuestionById(Long id);
    void verifyQuestionOwner(Question question, Long memberId);
}
