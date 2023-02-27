package com.jmc.stackoverflowbe.question.service;

import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.entity.Question.StateGroup;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {
    private final Question question = Question.builder()
        .questionId(0L)
        .questionContent("contents for service test")
        .memberId(0L)
        .questionTitle("service test")
        .views(0L)
        .votes(0)
        .state(StateGroup.ACTIVE)
        .selection(false)
        .answers(0L)
        .build();



}