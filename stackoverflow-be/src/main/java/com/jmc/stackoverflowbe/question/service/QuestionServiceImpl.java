package com.jmc.stackoverflowbe.question.service;

import com.jmc.stackoverflowbe.global.exception.BusinessLogicException;
import com.jmc.stackoverflowbe.global.exception.ExceptionCode;
import com.jmc.stackoverflowbe.question.dto.QuestionDto;
import com.jmc.stackoverflowbe.question.dto.QuestionDto.Response;
import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.entity.Question.StateGroup;
import com.jmc.stackoverflowbe.question.mapper.QuestionMapper;
import com.jmc.stackoverflowbe.question.repository.QuestionRepository;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;
    private final QuestionMapper mapper;

    //질문 생성
    @Override
    public Question createQuestion(QuestionDto.Post post){
        //질문 식별자로 검증이 필요할시 추가
        //verifyExistQuestion(question.getQuestionId());
        //포스트 객체 엔티티로 매핑
        Question question = mapper.postDtoToQuestion(post);
        //state 상태설정
        question.setState(StateGroup.ACTIVE);
        //질문 db에 저장
        return questionRepository.save(question);
    }
    //질문 수정
    @Override
    public Question updateQuestion(QuestionDto.Patch patch, Long questionId){
        Question question = mapper.patchDtoToQuestion(patch);
        //db에 저장된 질문인지 확인
        Question obtainedQuestion = findExistQuestionById(questionId);
        //수정할 내용이 있으면 수정 아니면 그대로
        Optional.ofNullable(question.getQuestionTitle())
            .ifPresent(title -> obtainedQuestion.setQuestionTitle(title));
        Optional.ofNullable(question.getQuestionContent())
            .ifPresent(content -> obtainedQuestion.setQuestionContent(content));
        //변경 사항 db에 적용
        return questionRepository.save(obtainedQuestion);
    }
    //질문 조회
    @Override
    public QuestionDto.Response getQuestion(Long questionId){
        //Id로 질문 조회
        QuestionDto.Response response =
            mapper.questionToResponseDto(findExistQuestionById(questionId));
        return response;
    }

    //질문 전체 조회
    @Override
    public Page<Response> getQuestions(int page, String sort){
        //모든 질문 리스트로 받기
        List<Question> questions = questionRepository.findAll();
        //활성된 질문만 리스트로
        for(Question currentQuestion : questions){
            if(currentQuestion.getState() != StateGroup.ACTIVE)
                questions.remove(currentQuestion);
        }
        //질문리스트 response 리스트로 매핑
        List<QuestionDto.Response> questionResponses =
            mapper.questionsToQuestionResponses(questions);
        //페이지네이션 적용
        Page<Response> responsePage = new PageImpl<>(questionResponses,
            PageRequest.of(0,15, Sort.by(sort).descending()),4);
        //반환
        return responsePage;
    }
    //질문 삭제
    @Override
    public void deleteQuestion(Long questionId){
        //db에 존재하는 질문인지 확인
        Question question = findExistQuestionById(questionId);
        //있으면 삭제
        question.setState(StateGroup.DELETED);
        questionRepository.save(question);
    }
    //조회수 증가, 투표기능, 선택 기능 추가해야할지?

    //질문이 존재하는지 확인
    @Override
    public Question findExistQuestionById(Long questionId){
        //질문id로 존재하는지 확인
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        //질문이 존재하지 않으면 예외 처리
        Question obtainedQuestion =
            optionalQuestion.orElseThrow(()->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        //질문의 상태가 삭제됨, 비활성화됨이면 예외 처리
        if(obtainedQuestion.getState() == StateGroup.DELETED ||
            obtainedQuestion.getState() == StateGroup.INACTIVE) {
            throw new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND);
        }
        //존재하면 해당 질문 반환
        return obtainedQuestion;
    }
}
// 질문 식별자로 존재하는지 확인하는 로직 필요할시 만들어 씀
//    private void verifyExistQuestion(long questionId){
//        Optional<Question> question = questionRepository.findById(questionId);
//        if(question.isPresent())
//            throw new BusinessLogicException(ExceptionCode.QUESTION_EXISTS);
//    }

