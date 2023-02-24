package com.jmc.stackoverflowbe.answer.service;

import com.jmc.stackoverflowbe.answer.entity.Answer;

public interface AnswerService {
    Answer createAnswer(Answer answer);
    Answer updateAnswer(Answer answer);
    Answer getAnswer(Long answerId);
    void deleteAnswer(Long answerId);
    Answer findExistId(Long answerId);

}
