package com.jmc.stackoverflowbe.comment.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
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
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.jmc.stackoverflowbe.comment.dto.CommentDto;
import com.jmc.stackoverflowbe.comment.entity.Comment;
import com.jmc.stackoverflowbe.comment.entity.Comment.CommentState;
import com.jmc.stackoverflowbe.comment.mapper.CommentMapper;
import com.jmc.stackoverflowbe.comment.service.CommentService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(CommentController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class CommentControllerTest {

    String BASE_URL = "/comments";

    private final Comment comment = Comment.builder()
        .commentId(1L)
        .commentContent("Sample comment.")
        .memberId(1L)
        .memberName("kimcoding")
        .qaId(1L)
        .commentState(CommentState.ACTIVE)
        .build();

    private final CommentDto.Post post = CommentDto.Post.builder()
        .commentContent("Sample comment.")
        .qaId(1L)
        .build();

    private final CommentDto.Patch patch = CommentDto.Patch.builder()
        .commentId(1L)
        .commentContent("Sample comment.")
        .qaId(1L)
        .build();

    private final CommentDto.Response response = CommentDto.Response.builder()
        .commentId(1L)
        .commentContent("Sample comment.")
        .memberId(1L)
        .memberName("kimcoding")
        .qaId(1L)
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
    void postCommentTest() throws Exception {
        String content = gson.toJson(post);

        given(mapper.postDtoToComment(Mockito.any(CommentDto.Post.class)))
            .willReturn(new Comment());
        given(commentService.createComment(Mockito.any(Comment.class)))
            .willReturn(comment);

        ResultActions actions = mockMvc.perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        ConstraintDescriptions postQuestionConstraints =
            new ConstraintDescriptions(CommentDto.Post.class);
        List<String> contentDescriptions = postQuestionConstraints
            .descriptionsForProperty("commentContent");
        List<String> qaIdDescriptions = postQuestionConstraints
            .descriptionsForProperty("qaId");

        actions
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", is(startsWith("/comments/"))))
            .andDo(document("Post-Comment",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    attributes(key("title").value("Fields for comment creation")),
                    fieldWithPath("commentContent")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value(contentDescriptions))
                        .description("댓글 내용"),
                    fieldWithPath("qaId")
                        .type(JsonFieldType.NUMBER)
                        .attributes(key("constraints").value(qaIdDescriptions))
                        .description("질답 식별자")),
                responseHeaders(
                    headerWithName(HttpHeaders.LOCATION)
                        .description("Header Location, 리소스의 URL")
                ))
            );
    }

    @DisplayName("댓글 수정")
    @Test
    void patchCommentTest() throws Exception {
        String content = gson.toJson(patch);

        given(mapper.patchDtoToComment(Mockito.any(CommentDto.Patch.class)))
            .willReturn(new Comment());
        given(commentService.updateComment(Mockito.any(Comment.class)))
            .willReturn(comment);

        ResultActions actions = mockMvc.perform(
            patch(BASE_URL + "/{comment-id}", patch.getCommentId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        ConstraintDescriptions patchQuestionConstraints =
            new ConstraintDescriptions(CommentDto.Patch.class);
        List<String> contentDescriptions = patchQuestionConstraints
            .descriptionsForProperty("commentContent");
        List<String> qaIdDescriptions = patchQuestionConstraints
            .descriptionsForProperty("qaId");

        actions
            .andExpect(status().isOk())
            .andDo(document("Patch-Comment",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("comment-id").description("댓글 식별자")
                ),
                requestFields(
                    attributes(key("title").value("Fields for comment revision")),
                    fieldWithPath("commentId")
                        .type(JsonFieldType.NUMBER)
                        .description("댓글 식별자")
                        .ignored(),
                    fieldWithPath("commentContent")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value(contentDescriptions))
                        .description("댓글 내용"),
                    fieldWithPath("qaId")
                        .type(JsonFieldType.NUMBER)
                        .attributes(key("constraints").value(qaIdDescriptions))
                        .description("질답 식별자")))
            );
    }

    @DisplayName("댓글 조회")
    @Test
    void getCommentTest() throws Exception {
        given(commentService.getComment(Mockito.anyLong()))
            .willReturn(new Comment());
        given(mapper.commentToResponseDto(Mockito.any(Comment.class)))
            .willReturn(response);

        ResultActions actions = mockMvc.perform(
            get(BASE_URL + "/{comment-id}", comment.getCommentId())
                .accept(MediaType.APPLICATION_JSON));

        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.commentId").exists())
            .andExpect(jsonPath("$.data.commentContent").exists())
            .andExpect(jsonPath("$.data.memberId").exists())
            .andExpect(jsonPath("$.data.memberName").exists())
            .andExpect(jsonPath("$.data.qaId").exists())
            .andExpect(jsonPath("$.data.commentState").exists())
            .andDo(document("Get-Comment",
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("comment-id").description("댓글 식별자")
                ),
                responseFields(
                    fieldWithPath("data")
                        .type(JsonFieldType.OBJECT)
                        .description("댓글"),
                    fieldWithPath("data.commentId")
                        .type(JsonFieldType.NUMBER)
                        .description("댓글 식별자"),
                    fieldWithPath("data.commentContent")
                        .type(JsonFieldType.STRING)
                        .description("댓글 내용"),
                    fieldWithPath("data.memberId")
                        .type(JsonFieldType.NUMBER)
                        .description("회원 식별자"),
                    fieldWithPath("data.memberName")
                        .type(JsonFieldType.STRING)
                        .description("회원 이름"),
                    fieldWithPath("data.qaId")
                        .type(JsonFieldType.NUMBER)
                        .description("질답 식별자"),
                    fieldWithPath("data.commentState")
                        .type(JsonFieldType.STRING)
                        .description("댓글 상태"),
                    fieldWithPath("data.createdAt")
                        .type(JsonFieldType.NULL)
                        .description("댓글 생성 날짜"),
                    fieldWithPath("data.modifiedAt")
                        .type(JsonFieldType.NULL)
                        .description("댓글 수정 날짜")
                ))
            );
    }

    @DisplayName("댓글 삭제")
    @Test
    void deleteCommentTest() throws Exception {
        doNothing().when(commentService).deleteComment(comment.getCommentId());

        ResultActions actions = mockMvc.perform(
            delete(BASE_URL + "/{comment-id}", comment.getCommentId())
                .accept(MediaType.APPLICATION_JSON));

        actions
            .andExpect(status().isNoContent())
            .andExpect(jsonPath("$.data").doesNotExist())
            .andDo(document("Delete-Comment",
                pathParameters(
                    parameterWithName("comment-id").description("댓글 식별자")
                ))
            );
    }
}
