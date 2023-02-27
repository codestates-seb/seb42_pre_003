package com.jmc.stackoverflowbe.question.service;

import com.jmc.stackoverflowbe.question.dto.QuestionDto;
import com.jmc.stackoverflowbe.question.dto.QuestionDto.Patch;
import com.jmc.stackoverflowbe.question.dto.QuestionDto.Response;
import com.jmc.stackoverflowbe.question.entity.Question;
import org.springframework.data.domain.Page;

public interface QuestionService {
    Question createQuestion(QuestionDto.Post post);
    Question updateQuestion(QuestionDto.Patch patch, Long questionId);
    QuestionDto.Response getQuestion(Long id);
    Page<Response> getQuestions(int page, String sort);
    void deleteQuestion(Long id);
    Question findExistQuestionById(Long id);
}
