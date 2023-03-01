package com.jmc.stackoverflowbe.comment.service;

import com.jmc.stackoverflowbe.answer.service.AnswerService;
import com.jmc.stackoverflowbe.comment.entity.Comment;
import com.jmc.stackoverflowbe.comment.entity.Comment.CommentState;
import com.jmc.stackoverflowbe.comment.repository.CommentRepository;
import com.jmc.stackoverflowbe.global.exception.BusinessLogicException;
import com.jmc.stackoverflowbe.global.exception.ExceptionCode;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.service.MemberService;
import com.jmc.stackoverflowbe.question.service.QuestionService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    // 댓글 생성 로직
    @Override
    public Comment createComment(Comment comment, String email) {
        // 댓글 생성 전 DB에 존재하는 멤버인지 확인하고 없으면 예외 처리.
        Member obtainedMember = memberService.findExistMemberByEmail(email);
        // 댓글에 member 저장.
        comment.setMember(obtainedMember);
        // 댓글 생성 전 DB에 존재하는 질문 혹은 답변인지 확인하고 없으면 예외 처리.
        verifyExistQAIdByEntity(comment);
        // 댓글 상태 활성.
        comment.setCommentState(CommentState.ACTIVE);

        // 댓글 DB에 저장.
        return commentRepository.save(comment);
    }

    // 댓글 수정 로직
    @Override
    public Comment updateComment(Comment comment, Long commentId, String email) {
        // DB에 존재하는 댓글인지 확인 후 있으면 obtainedComment 변수에 저장.
        Comment obtainedComment = findExistCommentById(commentId);

        // 해당 댓글을 수정할 권한이 있는 멤버(작성자)인지 확인.
        verifyAuthorizedMemberForComment(obtainedComment, email);

        // 댓글 내용 수정 후 obtainedComment에 저장.
        Optional.ofNullable(comment.getCommentContent())
            .ifPresent(content -> obtainedComment.setCommentContent(content));

        // 수정 사항 DB에 저장.
        return commentRepository.save(obtainedComment);
    }

    // 댓글 리스트 조회 로직
    @Override
    public List<Comment> getComments(String qaType, Long qaId) {
        // 지정된 타입(질문/답변)의 식별자로 DB에서 식별자와 연결된 모든 댓글들을 가져온다.
        List<Comment> comments = findExistCommentsByQAId(qaType, qaId);

        return comments;
    }

    // 댓글 삭제 로직
    @Override
    public void deleteComment(Long commentId, String email) {
        // DB에 존재하는 댓글인지 확인 후 있으면 obtainedComment 변수에 저장.
        Comment obtainedComment = findExistCommentById(commentId);

        // 해당 댓글을 삭제할 권한이 있는 멤버(작성자)인지 확인.
        verifyAuthorizedMemberForComment(obtainedComment, email);

        // 댓글 상태 삭제로 변경.
        obtainedComment.setCommentState(CommentState.DELETED);

        // 수정 사항 DB에 저장.
        commentRepository.save(obtainedComment);
    }

    // DB에 존재하는 댓글인지 확인하는 로직.
    @Override
    public Comment findExistCommentById(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        // 확인 후 없으면 예외 처리.
        Comment obtainedComment = optionalComment.orElseThrow(() ->
            new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        if (obtainedComment.getCommentState() == CommentState.INACTIVE ||
            obtainedComment.getCommentState() == CommentState.DELETED) {
            throw new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND);
        }

        return obtainedComment;
    }

    // Dto에서 변경된 entity 내의 식별자 정보를 통해 질문/답변이 존재하는지 확인하는 로직.
    void verifyExistQAIdByEntity(Comment comment) {
        // 답변 식별자가 null일 경우 questionService, 질문 식별자가 null일 경우 answerService에서
        // 질문/답변이 존재하는지 확인한다.
        if (comment.getAnswer().getAnswerId() == null) {
            comment.setAnswer(null);
            questionService.findExistQuestionById(comment.getQuestion().getQuestionId());
        } else if (comment.getQuestion().getQuestionId() == null) {
            comment.setQuestion(null);
            answerService.findExistAnswerById(comment.getAnswer().getAnswerId());
        }
    }

    // 질문인지 답변인지를 나타내는 qaType과 그 식별자를 통해 해당 식별자에 등록된 모든 댓글들을 반환하는 로직.
    List<Comment> findExistCommentsByQAId(String qaType, Long qaId) {
        // 연관 관계 매핑 이후 Question.questionId 혹은
        // Answer.answerId로 연결되므로 query를 아래와 같이 생성.
        List<Comment> comments;
        if (qaType.equals("Question")) {
            comments = commentRepository.findAllByQuestionQuestionId(qaId);
        } else {
            comments = commentRepository.findAllByAnswerAnswerId(qaId);
        }

        // Java stream으로 CommentState가 ACTIVE인 것만 분리.
        List<Comment> sortedComments = comments.stream()
            .filter(comment -> comment.getCommentState() == CommentState.ACTIVE)
            .collect(Collectors.toList());

        return sortedComments;
    }

    void verifyAuthorizedMemberForComment(Comment comment, String email) {
        if (email != comment.getMember().getEmail()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_AUTHORIZED);
        }
    }
}
