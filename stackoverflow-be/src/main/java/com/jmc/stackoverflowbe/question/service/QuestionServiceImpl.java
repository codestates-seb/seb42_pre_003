package com.jmc.stackoverflowbe.question.service;

import com.jmc.stackoverflowbe.global.exception.BusinessLogicException;
import com.jmc.stackoverflowbe.global.exception.ExceptionCode;
import com.jmc.stackoverflowbe.question.dto.QuestionDto;
import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.entity.Question.StateGroup;
import com.jmc.stackoverflowbe.question.mapper.QuestionMapper;
import com.jmc.stackoverflowbe.question.repository.QuestionRepository;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;
    private final QuestionMapper mapper;

    @Override
    public Question createQuestion(Question question){
        return questionRepository.save(question);
    }


    @Override
    public Question updateQuestion(Question question){return null;}
    @Override
    public Question getQuestion(Long questionId){
        return Question.builder()
            .questionId(0L)
            .questionTitle("Question title for stub")
            .memberId(0L)
            .questionContent("Question contents for stub")
            .state(StateGroup.ACTIVE)
            .votes(0)
            .selection(false)
            .answers(0L)
            .views(0L)
            .build();
    }

    @Override
    public Page<Question> getQuestions(int page, String sort){
        return questionRepository.findAll(
            PageRequest.of(page, 15, Sort.by(sort).descending()));
    }


    @Override
    public void deleteQuestion(Long questionId){

    }

    //질문이 존재하는지 확인
    @Override
    public Question findExistId(Long questionId){
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion =
            optionalQuestion.orElseThrow(()->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        return findQuestion;
    }
}
