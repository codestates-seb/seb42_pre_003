package com.jmc.stackoverflowbe.question.service;

import com.jmc.stackoverflowbe.global.exception.BusinessLogicException;
import com.jmc.stackoverflowbe.global.exception.ExceptionCode;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.repository.MemberRepository;
import com.jmc.stackoverflowbe.member.service.MemberService;
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
    private final MemberService memberService;


    //질문 생성
    @Override
    public Question createQuestion(Question question, Long memberId){
        // 질문을 등록하려는 멤버가 존재하는지 확인
        Member obtainedMember = memberService.findExistMemberById(memberId);
        // 질문에 멤버 설정
        question.setMember(obtainedMember);
        //state 상태설정
        question.setState(StateGroup.ACTIVE);
        //질문 db에 저장
        return questionRepository.save(question);
    }
    //질문 수정
    @Override
    public Question updateQuestion(Question question, Long memberId){
        //db에 저장된 질문인지 확인
        Question obtainedQuestion = findExistQuestionById(question.getQuestionId());
        //질문 소유자인지 확인
        verifyQuestionOwner(obtainedQuestion,memberId);
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
    public Question getQuestion(Long questionId){
        //질문 존재하는지 확인
        Question obtainedQuestion = findExistQuestionById(questionId);
        //있으면 반환
        return obtainedQuestion;
    }

    //질문 전체 조회
    @Override
    public Page<Question> getQuestions(int page, String sort){
        //모든 질문 리스트로 받기
        List<Question> questions = questionRepository.findAll();
        //활성된 질문만 리스트로
        for(Question currentQuestion : questions){
            if(currentQuestion.getState() != StateGroup.ACTIVE)
                questions.remove(currentQuestion);
        }
        //페이지네이션 적용 &반환
        return new PageImpl<>(questions,
            PageRequest.of(page, 15, Sort.by(sort).descending()), 2);
    }
    //질문 삭제
    @Override
    public void deleteQuestion(Long questionId, Long memberId){
        //db에 존재하는 질문인지 확인
        Question question = findExistQuestionById(questionId);
        //질문 소유자인지 확인
        verifyQuestionOwner(question,memberId);
        //있으면 삭제
        question.setState(StateGroup.DELETED);
        questionRepository.save(question);
    }

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

    @Override
    public void verifyQuestionOwner(Question question, Long memberId){
        if(memberId != question.getMember().getMemberId()){
            throw new BusinessLogicException(ExceptionCode.MEMBER_UNAUTHORIZED);
        }
    };
}
// 질문 식별자로 존재하는지 확인하는 로직 필요할시 만들어 씀
//    private void verifyExistQuestion(long questionId){
//        Optional<Question> question = questionRepository.findById(questionId);
//        if(question.isPresent())
//            throw new BusinessLogicException(ExceptionCode.QUESTION_EXISTS);
//    }

