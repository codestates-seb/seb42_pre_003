package com.jmc.stackoverflowbe.comment.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import com.jmc.stackoverflowbe.answer.service.AnswerService;
import com.jmc.stackoverflowbe.comment.dto.CommentDto;
import com.jmc.stackoverflowbe.comment.entity.Comment;
import com.jmc.stackoverflowbe.comment.entity.Comment.CommentState;
import com.jmc.stackoverflowbe.comment.mapper.CommentMapper;
import com.jmc.stackoverflowbe.comment.repository.CommentRepository;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import com.jmc.stackoverflowbe.member.service.MemberService;
import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.entity.Question.StateGroup;
import com.jmc.stackoverflowbe.question.service.QuestionService;
import java.util.List;
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

    Member member = Member.builder()
        .memberId(1L)
        .email("hgd@gmail.com")
        .name("홍길동")
        .state(MemberState.ACTIVE)
        .build();

    Question question = Question.builder()
        .questionId(1L)
        .questionTitle("Question title for stub")
        .memberId(1L)
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

    private final CommentDto.Post post = CommentDto.Post.builder()
        .commentContent("Sample comment.")
        .questionId(1L)
        .answerId(1L)
        .build();

    private final CommentDto.Patch patch = CommentDto.Patch.builder()
        .commentContent("Sample comment.")
        .build();

    private final CommentDto.Response response1 = CommentDto.Response.builder()
        .commentId(1L)
        .commentContent("Sample comment.")
        .memberId(1L)
        .memberName("kimcoding")
        .questionId(1L)
        .answerId(null)
        .commentState(CommentState.ACTIVE)
        .build();

    private final CommentDto.Response response2 = CommentDto.Response.builder()
        .commentId(2L)
        .commentContent("Sample comment 2.")
        .memberId(1L)
        .memberName("kimcoding")
        .questionId(1L)
        .answerId(null)
        .commentState(CommentState.ACTIVE)
        .build();

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentMapper mapper;

    @Mock
    private MemberService memberService;

    @Mock
    private QuestionService questionService;

    @Mock
    private AnswerService answerService;

    @Spy
    @InjectMocks
    private CommentServiceImpl commentService;

    @DisplayName("Service단 댓글 생성 로직")
    @Test
    public void createCommentTest() {
        given(mapper.postDtoToComment(Mockito.any(CommentDto.Post.class)))
            .willReturn(new Comment());
//        given(memberService.findExistMemberById(Mockito.anyLong()))
//            .willReturn(new Member());
//        given(questionService.findExistQuestionById(Mockito.anyLong()))
//            .willReturn(new Question());
//        ReflectionTestUtils.invokeMethod(commentService, "verifyExistQAIdByEntity", comment);
        given(commentRepository.save(Mockito.any(Comment.class)))
            .willReturn(comment);

        Executable executable = () -> commentService.createComment(post);

        assertDoesNotThrow(executable);
    }

    @DisplayName("Service단 댓글 수정 로직")
    @Test
    public void updateCommentTest() {
        given(mapper.patchDtoToComment(Mockito.any(CommentDto.Patch.class)))
            .willReturn(new Comment());
        given(commentRepository.findById(Mockito.anyLong()))
            .willReturn(Optional.of(comment));
        given(commentRepository.save(Mockito.any(Comment.class)))
            .willReturn(comment);

        Executable executable = () -> commentService.updateComment(patch, comment.getCommentId());

        assertDoesNotThrow(executable);
    }

    @DisplayName("Service단 댓글 리스트 조회 로직")
    @Test
    public void getCommentsTest() {
        ReflectionTestUtils.invokeMethod(commentService, "findExistCommentsByQAId",
            "Question", comment.getQuestion().getQuestionId());
        given(mapper.commentsToResponseDtos(Mockito.anyList()))
            .willReturn(List.of(response1, response2));

        Executable executable = () -> commentService.getComments("Question",
            comment.getCommentId());

        assertDoesNotThrow(executable);
    }

    @DisplayName("Service단 댓글 삭제 로직")
    @Test
    public void deleteCommentTest() {
        given(commentRepository.findById(Mockito.anyLong()))
            .willReturn(Optional.of(new Comment()));
        given(commentRepository.save(Mockito.any(Comment.class)))
            .willReturn(deletedComment);

        Executable executable = () -> commentService.deleteComment(comment.getCommentId());

        assertEquals(deletedComment.getCommentState(), CommentState.DELETED);
        assertDoesNotThrow(executable);
    }
}
