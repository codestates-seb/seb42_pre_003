package com.jmc.stackoverflowbe.comment.service;

import com.jmc.stackoverflowbe.answer.service.AnswerService;
import com.jmc.stackoverflowbe.comment.dto.CommentDto.Patch;
import com.jmc.stackoverflowbe.comment.dto.CommentDto.Post;
import com.jmc.stackoverflowbe.comment.dto.CommentDto.Response;
import com.jmc.stackoverflowbe.comment.entity.Comment;
import com.jmc.stackoverflowbe.comment.entity.Comment.CommentState;
import com.jmc.stackoverflowbe.comment.mapper.CommentMapper;
import com.jmc.stackoverflowbe.comment.repository.CommentRepository;
import com.jmc.stackoverflowbe.member.service.MemberService;
import com.jmc.stackoverflowbe.question.service.QuestionService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper mapper;
    private final MemberService memberService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    @Override
    public Comment createComment(Post post) {
        Comment comment = mapper.postDtoToComment(post);

//        memberService.findExistId(comment.getMemberId());
//        verifyExistQAIdByEntity(comment);

        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Patch patch, Long commentId) {
        Comment comment = mapper.patchDtoToComment(patch);
        Comment obtainedComment = findCommentByExistId(commentId);

        Optional.ofNullable(comment.getCommentContent())
            .ifPresent(content -> obtainedComment.setCommentContent(content));

        return commentRepository.save(obtainedComment);
    }

    @Override
    public List<Response> getComments(String qaType, Long qaId) {
        List<Comment> comments = findCommentsByExistQAId(qaType, qaId);

        return mapper.commentsToResponseDtos(comments);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment obtainedComment = findCommentByExistId(commentId);
        obtainedComment.setCommentState(CommentState.DELETED);

        commentRepository.save(obtainedComment);
    }

    @Override
    public Comment findCommentByExistId(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Comment obtainedComment = optionalComment.orElseThrow(() ->
            new RuntimeException());

        return obtainedComment;
    }

    private void verifyExistQAIdByEntity(Comment comment) {
        if (comment.getAnswerId() == null) {
            questionService.findExistId(comment.getQuestionId());
        } else if (comment.getQuestionId() == null) {
            answerService.findExistId(comment.getAnswerId());
        }
    }

    private List<Comment> findCommentsByExistQAId(String qaType, Long qaId) {
//        List<Comment> comments;
//        if (qaType.equals("Question")) {
//            comments = commentRepository.findAllByQuestionId(qaId);
//        } else {
//            comments = commentRepository.findAllByAnswerId(qaId);
//        }

        // Temporary logic
        List<Comment> comments = commentRepository.findAll();

        return comments;
    }
}
