package com.jmc.stackoverflowbe.answer.service;

import com.jmc.stackoverflowbe.answer.entity.Answer;
import java.util.List;

public interface AnswerService {
    Answer createAnswer(Answer answer, Long memberId);
    Answer updateAnswer(Answer answer, Long answerId, Long memberId);
    List<Answer> getAnswers(Long questionId);
    void deleteAnswer(Long answerId, Long memberId);
    Answer findExistAnswerById(Long answerId);

}
