package com.jmc.stackoverflowbe.comment.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.jmc.stackoverflowbe.comment.dto.CommentDto;
import com.jmc.stackoverflowbe.comment.entity.Comment;
import com.jmc.stackoverflowbe.comment.entity.Comment.CommentState;
import com.jmc.stackoverflowbe.comment.mapper.CommentMapper;
import com.jmc.stackoverflowbe.comment.service.CommentService;
import com.jmc.stackoverflowbe.global.WithMockCustomMember;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.entity.Question.StateGroup;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@WebMvcTest(CommentController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@WithMockUser(username = "kimcoding@gmail.com", roles = {"USER"})
public class CommentControllerTest {

    String BASE_URL = "/comments";

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

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CommentService commentService;

    @MockBean
    CommentMapper mapper;

    @Autowired
    Gson gson;

    @DisplayName("댓글 생성")
    @Test
    @WithMockCustomMember
    void postCommentTest() throws Exception {
        String content = gson.toJson(post);

        given(mapper.postDtoToComment(Mockito.any(CommentDto.Post.class)))
            .willReturn(new Comment());
        given(commentService.createComment(Mockito.any(Comment.class), Mockito.anyLong()))
            .willReturn(comment);

        ResultActions actions = mockMvc.perform(
            post(BASE_URL)
                .with(csrf())
                .header("Authorization", "")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        ConstraintDescriptions postQuestionConstraints =
            new ConstraintDescriptions(CommentDto.Post.class);
        List<String> contentDescriptions = postQuestionConstraints
            .descriptionsForProperty("commentContent");
        List<String> questionIdDescriptions = postQuestionConstraints
            .descriptionsForProperty("questionId");
        List<String> answerIdDescriptions = postQuestionConstraints
            .descriptionsForProperty("answerId");

        actions
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", is(startsWith("/comments/"))))
            .andDo(document("Post-Comment",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    attributes(key("title")
                        .value("Headers for user revision")),
                    headerWithName("Authorization")
                        .attributes(key("constraints").value("Berrer {accessToken}"))
                        .optional()
                        .description("액세스 토큰")
                ),
                requestFields(
                    attributes(key("title").value("Fields for comment creation")),
                    fieldWithPath("commentContent")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value(contentDescriptions))
                        .description("댓글 내용"),
                    fieldWithPath("questionId")
                        .type(JsonFieldType.NUMBER)
                        .attributes(key("constraints").value(questionIdDescriptions))
                        .description("질문 식별자")
                        .optional(),
                    fieldWithPath("answerId")
                        .type(JsonFieldType.NUMBER)
                        .attributes(key("constraints").value(answerIdDescriptions))
                        .description("답변 식별자")
                        .optional()),
                responseHeaders(
                    headerWithName(HttpHeaders.LOCATION)
                        .description("Header Location, 리소스의 URL")
                ))
            );
    }

    @DisplayName("댓글 수정")
    @Test
    @WithMockCustomMember
    void patchCommentTest() throws Exception {
        String content = gson.toJson(patch);

        given(mapper.patchDtoToComment(Mockito.any(CommentDto.Patch.class)))
            .willReturn(new Comment());
        given(commentService.updateComment(Mockito.any(Comment.class), Mockito.anyLong(),
            Mockito.anyLong()))
            .willReturn(comment);

        ResultActions actions = mockMvc.perform(
            patch(BASE_URL + "/{comment-id}", comment.getCommentId())
                .with(csrf())
                .header("Authorization", "")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        ConstraintDescriptions patchQuestionConstraints =
            new ConstraintDescriptions(CommentDto.Patch.class);
        List<String> contentDescriptions = patchQuestionConstraints
            .descriptionsForProperty("commentContent");

        actions
            .andExpect(status().isOk())
            .andDo(document("Patch-Comment",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("comment-id").description("댓글 식별자")
                ),
                requestHeaders(
                    attributes(key("title")
                        .value("Headers for user revision")),
                    headerWithName("Authorization")
                        .attributes(key("constraints").value("Berrer {accessToken}"))
                        .optional()
                        .description("액세스 토큰")
                ),
                requestFields(
                    attributes(key("title").value("Fields for comment revision")),
                    fieldWithPath("commentContent")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value(contentDescriptions))
                        .description("댓글 내용")
                ))
            );
    }

    @DisplayName("댓글 조회")
    @Test
    void getCommentsTest() throws Exception {
        given(commentService.getComments(Mockito.anyString(), Mockito.anyLong()))
            .willReturn(List.of(new Comment(), new Comment()));
        given(mapper.commentsToResponseDtos(Mockito.anyList()))
            .willReturn(List.of(response1, response2));

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("qaType", "Question");
        queryParams.add("qaId", "1");

        ResultActions actions = mockMvc.perform(
            get(BASE_URL)
                .params(queryParams)
                .accept(MediaType.APPLICATION_JSON));

        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.*").exists())
            .andExpect(jsonPath("$.data[0].commentId").exists())
            .andExpect(jsonPath("$.data[0].commentContent").exists())
            .andExpect(jsonPath("$.data[0].memberId").exists())
            .andExpect(jsonPath("$.data[0].memberName").exists())
            .andExpect(jsonPath("$.data[0].questionId").exists())
            .andExpect(jsonPath("$.data[0].answerId").doesNotExist())
            .andExpect(jsonPath("$.data[0].commentState").exists())
            .andExpect(jsonPath("$.data[1].commentId").exists())
            .andExpect(jsonPath("$.data[1].commentContent").exists())
            .andExpect(jsonPath("$.data[1].memberId").exists())
            .andExpect(jsonPath("$.data[1].memberName").exists())
            .andExpect(jsonPath("$.data[1].questionId").exists())
            .andExpect(jsonPath("$.data[1].answerId").doesNotExist())
            .andExpect(jsonPath("$.data[1].commentState").exists())
            .andDo(document("Get-Comments",
                preprocessResponse(prettyPrint()),
                requestParameters(
                    parameterWithName("qaType").description("질문/답변 항목 표시"),
                    parameterWithName("qaId").description("질문/답변 식별자")
                ),
                responseFields(
                    fieldWithPath("data[]")
                        .type(JsonFieldType.ARRAY)
                        .description("댓글 리스트"),
                    fieldWithPath("data[].commentId")
                        .type(JsonFieldType.NUMBER)
                        .description("댓글 식별자"),
                    fieldWithPath("data[].commentContent")
                        .type(JsonFieldType.STRING)
                        .description("댓글 내용"),
                    fieldWithPath("data[].memberId")
                        .type(JsonFieldType.NUMBER)
                        .description("회원 식별자"),
                    fieldWithPath("data[].memberName")
                        .type(JsonFieldType.STRING)
                        .description("회원 이름"),
                    fieldWithPath("data[].questionId")
                        .type(JsonFieldType.NUMBER)
                        .description("질문 식별자"),
                    fieldWithPath("data[].answerId")
                        .type(JsonFieldType.NULL)
                        .description("답변 식별자"),
                    fieldWithPath("data[].commentState")
                        .type(JsonFieldType.STRING)
                        .description("댓글 상태"),
                    fieldWithPath("data[].createdAt")
                        .type(JsonFieldType.NULL)
                        .description("댓글 생성 날짜"),
                    fieldWithPath("data[].modifiedAt")
                        .type(JsonFieldType.NULL)
                        .description("댓글 수정 날짜")
                ))
            );
    }

    @DisplayName("댓글 삭제")
    @Test
    @WithMockCustomMember
    void deleteCommentTest() throws Exception {
        doNothing().when(commentService)
            .deleteComment(comment.getCommentId(), member.getMemberId());

        ResultActions actions = mockMvc.perform(
            delete(BASE_URL + "/{comment-id}", comment.getCommentId())
                .with(csrf())
                .header("Authorization", "")
                .accept(MediaType.APPLICATION_JSON));

        actions
            .andExpect(status().isNoContent())
            .andExpect(jsonPath("$.data").doesNotExist())
            .andDo(document("Delete-Comment",
                pathParameters(
                    parameterWithName("comment-id").description("댓글 식별자")
                ),
                requestHeaders(
                    attributes(key("title")
                        .value("Headers for user revision")),
                    headerWithName("Authorization")
                        .attributes(key("constraints").value("Berrer {accessToken}"))
                        .optional()
                        .description("액세스 토큰")
                )
            ));
    }
}
