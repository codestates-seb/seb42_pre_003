package com.jmc.stackoverflowbe.vote.service;

import com.jmc.stackoverflowbe.answer.service.AnswerService;
import com.jmc.stackoverflowbe.global.exception.BusinessLogicException;
import com.jmc.stackoverflowbe.global.exception.ExceptionCode;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.service.MemberService;
import com.jmc.stackoverflowbe.question.service.QuestionService;
import com.jmc.stackoverflowbe.vote.entity.Vote;
import com.jmc.stackoverflowbe.vote.repository.VoteRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final MemberService memberService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    // 투표 생성 로직
    @Override
    public Vote createVote(Vote vote, Long memberId) {
        // 투표 전 DB에 존재하는 멤버인지 확인하고 없으면 예외 처리.
        Member obtainedMember = memberService.findExistMemberById(memberId);
        // 투표 entity에 member 저장.
        vote.setMember(obtainedMember);
        // 투표 전 DB에 존재하는 질문 혹은 답변인지 확인하고 없으면 예외 처리.
        verifyExistQAIdByEntity(vote);

        // 투표 DB에 저장.
        return voteRepository.save(vote);
    }

    // 투표 수정 로직
    @Override
    public Vote updateVote(Vote vote, Long voteId, Long memberId) {
        // DB에 존재하는 투표인지 확인 후 있으면 obtainedVote 변수에 저장.
        Vote obtainedVote = findExistVoteById(voteId);

        // 해당 댓글을 수정할 권한이 있는 멤버(작성자)인지 확인.
        verifyAuthorizedMemberForVote(obtainedVote, memberId);

        // 댓글 내용 수정 후 obtainedVote에 저장.
        Optional.ofNullable(vote.getVote())
            .ifPresent(vt -> obtainedVote.setVote(vt));
        // 수정 사항 DB에 저장.
        return voteRepository.save(obtainedVote);
    }

    // 투표 리스트 조회 로직
    @Override
    public List<Vote> getVotes(String qaType, Long qaId) {
        // 지정된 타입(질문/답변)의 식별자로 DB에서 식별자와 연결된 모든 투표들을 가져온다.
        List<Vote> votes = findExistVotesByQAId(qaType, qaId);

        return votes;
    }

    // 투표 삭제 로직
    @Override
    public void deleteVote(Long voteId, Long memberId) {
        // DB에 존재하는 투표인지 확인 후 있으면 obtainedVote 변수에 저장.
        Vote obtainedVote = findExistVoteById(voteId);

        // 해당 투표를 삭제할 권한이 있는 멤버(작성자)인지 확인.
        verifyAuthorizedMemberForVote(obtainedVote, memberId);

        // 투표 삭제.
        voteRepository.delete(obtainedVote);
    }

    // DB에 존재하는 투표인지 확인하는 로직.
    @Override
    public Vote findExistVoteById(Long voteId) {
        Optional<Vote> optionalVote = voteRepository.findById(voteId);
        // 확인 후 없으면 예외 처리.
        Vote obtainedVote = optionalVote.orElseThrow(() ->
            new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND));

        return obtainedVote;
    }

    // Dto에서 변경된 entity 내의 식별자 정보를 통해 질문/답변이 존재하는지 확인하는 로직.
    void verifyExistQAIdByEntity(Vote vote) {
        // 답변 식별자가 null일 경우 questionService, 질문 식별자가 null일 경우 answerService에서
        // 질문/답변이 존재하는지 확인한다.
        if (vote.getAnswer().getAnswerId() == null) {
            vote.setAnswer(null);
            questionService.findExistQuestionById(vote.getQuestion().getQuestionId());
        } else if (vote.getQuestion().getQuestionId() == null) {
            vote.setQuestion(null);
            answerService.findExistAnswerById(vote.getAnswer().getAnswerId());
        }
    }

    // 질문인지 답변인지를 나타내는 qaType과 그 식별자를 통해 해당 식별자에 등록된 모든 투표 결과들을 반환하는 로직.
    List<Vote> findExistVotesByQAId(String qaType, Long qaId) {
        // 연관 관계 매핑 이후 Question.questionId 혹은
        // Answer.answerId로 연결되므로 query를 아래와 같이 생성.
        List<Vote> votes;
        if (qaType.equals("Question")) {
            votes = voteRepository.findAllByQuestionQuestionId(qaId);
        } else {
            votes = voteRepository.findAllByAnswerAnswerId(qaId);
        }

        return votes;
    }

    void verifyAuthorizedMemberForVote(Vote vote, Long memberId) {
        if (memberId != vote.getMember().getMemberId()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_UNAUTHORIZED);
        }
    }
}
