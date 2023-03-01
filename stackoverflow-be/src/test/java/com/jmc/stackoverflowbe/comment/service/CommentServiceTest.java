package com.jmc.stackoverflowbe.comment.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import com.jmc.stackoverflowbe.comment.entity.Comment;
import com.jmc.stackoverflowbe.comment.entity.Comment.CommentState;
import com.jmc.stackoverflowbe.comment.repository.CommentRepository;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import com.jmc.stackoverflowbe.member.service.MemberService;
import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.entity.Question.StateGroup;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    private final Member member = Member.builder()
        .memberId(1L)
        .email("hgd@gmail.com")
        .name("홍길동")
        .state(MemberState.ACTIVE)
        .build();

    private final Question question = Question.builder()
        .questionId(1L)
        .questionTitle("Question title for stub")
        .member(member)
        .questionContent("Question contents for stub")
        .state(StateGroup.ACTIVE)
        .votes(0)
        .selection(false)
        .answers(0L)
        .views(0L)
        .build();

    private final Comment comment = Comment.builder()
        .commentId(1L)
        .commentContent("Sample comment.")
        .member(member)
        .question(question)
        .commentState(CommentState.ACTIVE)
        .build();

    private final Comment deletedComment = Comment.builder()
        .commentId(1L)
        .commentContent("Sample comment.")
        .member(member)
        .question(question)
        .commentState(CommentState.DELETED)
        .build();

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private MemberService memberService;

    @Spy
    @InjectMocks
    private CommentServiceImpl commentService;

    @DisplayName("Service단 댓글 생성 로직")
    @Test
    public void createCommentTest() {
        given(memberService.findExistMemberByEmail(Mockito.anyString()))
            .willReturn(new Member());
        doNothing().when(commentService).verifyExistQAIdByEntity(comment);
        given(commentRepository.save(Mockito.any(Comment.class)))
            .willReturn(comment);

        Executable executable = () -> commentService.createComment(comment, member.getEmail());

        assertDoesNotThrow(executable);
    }

    @DisplayName("Service단 댓글 수정 로직")
    @Test
    public void updateCommentTest() {
        given(commentRepository.findById(Mockito.anyLong()))
            .willReturn(Optional.of(new Comment()));
        doNothing().when(commentService)
            .verifyAuthorizedMemberForComment(Mockito.any(Comment.class), Mockito.anyString());
        given(commentRepository.save(Mockito.any(Comment.class)))
            .willReturn(comment);

        Executable executable = () ->
            commentService.updateComment(comment, comment.getCommentId(), member.getEmail());

        assertDoesNotThrow(executable);
    }

    @DisplayName("Service단 댓글 리스트 조회 로직")
    @Test
    public void getCommentsTest() {
        ReflectionTestUtils.invokeMethod(commentService, "findExistCommentsByQAId",
            "Question", comment.getQuestion().getQuestionId());

        Executable executable = () -> commentService.getComments("Question",
            comment.getCommentId());

        assertDoesNotThrow(executable);
    }

    @DisplayName("Service단 댓글 삭제 로직")
    @Test
    public void deleteCommentTest() {
        given(commentRepository.findById(Mockito.anyLong()))
            .willReturn(Optional.of(new Comment()));
        doNothing().when(commentService)
            .verifyAuthorizedMemberForComment(Mockito.any(Comment.class), Mockito.anyString());
        given(commentRepository.save(Mockito.any(Comment.class)))
            .willReturn(deletedComment);

        Executable executable = () -> commentService.deleteComment(comment.getCommentId(),
            member.getEmail());

        assertEquals(deletedComment.getCommentState(), CommentState.DELETED);
        assertDoesNotThrow(executable);
    }
}
