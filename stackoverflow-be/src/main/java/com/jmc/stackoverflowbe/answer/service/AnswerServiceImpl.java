package com.jmc.stackoverflowbe.answer.service;

import com.jmc.stackoverflowbe.answer.dto.AnswerDto.Patch;
import com.jmc.stackoverflowbe.answer.dto.AnswerDto.Post;
import com.jmc.stackoverflowbe.answer.dto.AnswerDto.Response;
import com.jmc.stackoverflowbe.answer.entity.Answer;
import com.jmc.stackoverflowbe.answer.entity.Answer.StateGroup;
import com.jmc.stackoverflowbe.answer.mapper.AnswerMapper;
import com.jmc.stackoverflowbe.answer.repository.AnswerRepository;
import com.jmc.stackoverflowbe.global.exception.BusinessLogicException;
import com.jmc.stackoverflowbe.global.exception.ExceptionCode;
import com.jmc.stackoverflowbe.member.service.MemberService;
import com.jmc.stackoverflowbe.question.service.QuestionService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerMapper mapper;
    private final MemberService memberService;
    private final QuestionService questionService;

    // 답변 생성 로직
    @Override
    public Answer createAnswer(Post post) {
        // Service단에서 dto를 entity로 변경.
        Answer answer = mapper.postDtoToAnswer(post);

        // 답변 생성 전 DB에 존재하는 멤버인지 확인하고 없으면 예외 처리.
        // memberService.findExistMemberById(comment.getMember().getMemberId());
        // 답변 생성 전 DB에 존재하는 질문인지 확인하고 없으면 예외 처리.
        // verifyExistQuestionIdByEntity(answer);
        // 답변 상태 활성.
        answer.setState(StateGroup.ACTIVE);

        return answerRepository.save(answer);
    }

    // 답변 수정 로직
    @Override
    public Answer updateAnswer(Patch patch, Long answerId) {
        // Service단에서 dto를 entity로 변경.
        Answer answer = mapper.patchDtoToAnswer(patch);
        // DB에 존재하는 답변인지 확인 후 있으면 obtainedAnswer 변수에 저장.
        Answer obtainedAnswer = findExistAnswerById(answerId);

        // 답변 내용 수정 후 obtainedAnswer에 저장.
        Optional.ofNullable(answer.getAnswerContent())
            .ifPresent(content -> obtainedAnswer.setAnswerContent(content));

        // 수정 사항 DB에 저장.
        return answerRepository.save(obtainedAnswer);
    }

    // 답변 리스트 조회 로직
    @Override
    public List<Response> getAnswers(Long questionId) {
        // 질문 식별자로 DB에서 식별자와 연결된 모든 댓글들을 가져온다.
        List<Answer> answers = findExistAnswersByQuestionId(questionId);

        // Service단에서 entity를 dto로 변경하여 return.
        return mapper.answersToResponseDtos(answers);
    }

    // 답변 삭제 로직
    @Override
    public void deleteAnswer(Long answerId) {
        // DB에 존재하는 답변인지 확인 후 있으면 obtainedAnswer 변수에 저장.
        Answer obtainedAnswer = findExistAnswerById(answerId);
        // 답변 상태 삭제로 변경.
        obtainedAnswer.setState(StateGroup.DELETED);

        // 수정 사항 DB에 저장.
        answerRepository.save(obtainedAnswer);
    }

    // DB에 존재하는 답변인지 확인하는 로직.
    @Override
    public Answer findExistAnswerById(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        // 확인 후 없으면 예외 처리.
        Answer obtainedAnswer = optionalAnswer.orElseThrow(() ->
            new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        return obtainedAnswer;
    }

    // Dto에서 변경된 entity 내의 식별자 정보를 통해 질문이 존재하는지 확인하는 로직.
    private void verifyExistQuestionIdByEntity(Answer answer) {
        // questionService에서 질문이 존재하는지 확인한다.
        // questionService.findExistQuestionById(answer.getQuestion().getQuestionId());
    }

    // 질문 식별자를 통해 해당 식별자에 등록된 모든 댓글들을 반환하는 로직.
    private List<Answer> findExistAnswersByQuestionId(Long questionId) {
        // 연관 관계 매핑 이후 Question.questionId로 연결되므로 query를 아래와 같이 생성.
        List<Answer> answers = answerRepository.findAllByQuestionQuestionId(questionId);

        // Java stream으로 StateGroup이 ACTIVE인 것만 분리.
        List<Answer> sortedAnswers = answers.stream()
            .filter(answer -> answer.getState().equals(StateGroup.ACTIVE))
            .collect(Collectors.toList());

        return sortedAnswers;
    }
}
