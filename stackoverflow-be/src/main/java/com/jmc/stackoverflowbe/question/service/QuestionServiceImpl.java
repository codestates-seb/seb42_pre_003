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

    //질문 생성
    @Override
    public Question createQuestion(Question question){
        //질문 아이디로 검증이 필요할시 추가
        //verifyExistQuestion(question.getQuestionId());
        //질문 db에 저장
        return questionRepository.save(question);
    }

    //질문 수정
    @Override
    public Question updateQuestion(Question question){
        //db에 저장된 질문인지 확인
        Question findQuestion = findExistQuestion(question.getQuestionId());
        //수정할 내용이 있으면 수정 아니면 그대로
        Optional.ofNullable(question.getQuestionTitle())
            .ifPresent(title -> findQuestion.setQuestionTitle(title));
        Optional.ofNullable(question.getQuestionContent())
            .ifPresent(content -> findQuestion.setQuestionContent(content));
        //변경 사항 db에 적용
        return questionRepository.save(findQuestion);
    }
    //질문 조회
    @Override
    public Question getQuestion(Long questionId){
        //Id로 질문 조회
        return findExistQuestion(questionId);
    }

    //질문 전체 조회
    @Override
    public Page<Question> getQuestions(int page, String sort){
        //전체를 찾아서 페이지네이션 적용 후 반환
        return questionRepository.findAll(
            PageRequest.of(page, 15, Sort.by(sort).descending()));
    }
    //질문 삭제
    @Override
    public void deleteQuestion(Long questionId){
        //db에 존재하는 질문인지 확인
        Question question = findExistQuestion(questionId);
        //있으면 삭제
        questionRepository.delete(question);
    }
    //조회수 증가, 투표기능, 선택 기능 추가해야할지?

    //질문이 존재하는지 확인
    @Override
    public Question findExistQuestion(Long questionId){
        //질문id로 존재하는지 확인
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        //질문이 존재하지 않으면 예외 처리
        Question findQuestion =
            optionalQuestion.orElseThrow(()->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        //존재하면 해당 질문 반환
        return findQuestion;
    }
// 질문 식별자로 존재하는지 확인하는 로직 필요할시 만들어 씀
//    private void verifyExistQuestion(long questionId){
//        Optional<Question> question = questionRepository.findById(questionId);
//        if(question.isPresent())
//            throw new BusinessLogicException(ExceptionCode.QUESTION_EXISTS);
//    }

}
