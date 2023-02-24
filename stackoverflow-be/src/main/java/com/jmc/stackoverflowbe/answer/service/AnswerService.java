package com.jmc.stackoverflowbe.answer.service;

import com.jmc.stackoverflowbe.answer.entity.Answer;
import java.util.List;

public interface AnswerService {
    Answer createAnswer(Answer answer);
    Answer updateAnswer(Answer answer);
    List<Answer> getAnswers(Long questionId);
    void deleteAnswer(Long answerId);
    Answer findExistId(Long answerId);

}