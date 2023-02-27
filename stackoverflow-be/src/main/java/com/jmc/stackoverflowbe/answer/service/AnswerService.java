package com.jmc.stackoverflowbe.answer.service;

import com.jmc.stackoverflowbe.answer.dto.AnswerDto;
import com.jmc.stackoverflowbe.answer.entity.Answer;
import java.util.List;

public interface AnswerService {
    Answer createAnswer(AnswerDto.Post post);
    Answer updateAnswer(AnswerDto.Patch patch, Long answerId);
    List<AnswerDto.Response> getAnswers(Long questionId);
    void deleteAnswer(Long answerId);
    Answer findExistAnswerById(Long answerId);

}
