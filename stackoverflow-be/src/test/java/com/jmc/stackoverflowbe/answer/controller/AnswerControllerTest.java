package com.jmc.stackoverflowbe.answer.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
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
import com.jmc.stackoverflowbe.answer.dto.AnswerDto;
import com.jmc.stackoverflowbe.answer.entity.Answer;
import com.jmc.stackoverflowbe.answer.entity.Answer.StateGroup;
import com.jmc.stackoverflowbe.answer.mapper.AnswerMapper;
import com.jmc.stackoverflowbe.answer.service.AnswerService;
import com.jmc.stackoverflowbe.global.WithMockCustomMember;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import com.jmc.stackoverflowbe.question.entity.Question;
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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(AnswerController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@WithMockUser(username = "kimcoding@gmail.com", roles = {"USER"})
public class AnswerControllerTest {

    String BASE_URL = "/answers";

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
        .state(Question.StateGroup.ACTIVE)
        .votes(0)
        .selection(false)
        .answers(0L)
        .views(0L)
        .build();

    private final Answer answer = Answer.builder()
        .answerId(1L)
        .answerContent("testing content")
        .state(StateGroup.ACTIVE)
        .votes(0L)
        .member(member)
        .question(question)
        .build();

    private final AnswerDto.Post post = AnswerDto.Post.builder()
        .questionId(1L)
        .answerContent("post testing content")
        .build();

    private final AnswerDto.Patch patch = AnswerDto.Patch.builder()
        .answerContent("patch testing content")
        .build();

    private final AnswerDto.Response response1 = AnswerDto.Response.builder()
        .answerId(1L)
        .questionId(1L)
        .memberId(1L)
        .answerContent("get testing content")
        .votes(0)
        .state(StateGroup.ACTIVE)
        .build();

    private final AnswerDto.Response response2 = AnswerDto.Response.builder()
        .answerId(2L)
        .questionId(1L)
        .memberId(1L)
        .answerContent("get testing content 2")
        .votes(0)
        .state(StateGroup.ACTIVE)
        .build();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AnswerService answerService;

    @MockBean
    AnswerMapper mapper;

    @Autowired
    Gson gson;

    @DisplayName("답변 생성")
    @Test
    @WithMockCustomMember
    void postAnswerTest() throws Exception {
        String content = gson.toJson(post);

        given(mapper.postDtoToAnswer(Mockito.any(AnswerDto.Post.class)))
            .willReturn(new Answer());
        given(answerService.createAnswer(Mockito.any(Answer.class), Mockito.anyLong()))
            .willReturn(answer);

        ResultActions actions = mockMvc.perform(
            post(BASE_URL)
                .with(csrf())
                .header("Authorization", "")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        actions
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", is(startsWith("/answers"))))
            .andDo(document("Post-Answer",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    attributes(key("title")
                        .value("Headers for user revision")),
                    headerWithName("Authorization")
                        .attributes(key("constraints").value("Bearer {accessToken}"))
                        .description("액세스 토큰")
                ),
                requestFields(
                    attributes(key("title").value("Fields for answer creation")),
                    fieldWithPath("questionId")
                        .type(JsonFieldType.NUMBER)
                        .attributes(key("constraints").value("질문"))
                        .description("작성된 질문"),
                    fieldWithPath("answerContent")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value("내용"))
                        .description("답변 내용")
                ))
            );
    }

    @DisplayName("답변 수정")
    @Test
    @WithMockCustomMember
    void patchAnswerTest() throws Exception {
        String content = gson.toJson(patch);

        given(mapper.patchDtoToAnswer(Mockito.any(AnswerDto.Patch.class)))
            .willReturn(new Answer());
        given(answerService.updateAnswer(Mockito.any(Answer.class), Mockito.anyLong(),
            Mockito.anyLong()))
            .willReturn(answer);

        ResultActions actions = mockMvc.perform(
            patch(BASE_URL + "/{answer-id}", answer.getAnswerId())
                .with(csrf())
                .header("Authorization", "")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        actions
            .andExpect(status().isOk())
            .andDo(document("Patch-Answer",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("answer-id")
                        .description("답변 식별자")),
                requestHeaders(
                    attributes(key("title")
                        .value("Headers for user revision")),
                    headerWithName("Authorization")
                        .attributes(key("constraints").value("Bearer {accessToken}"))
                        .description("액세스 토큰")
                ),
                requestFields(
                    attributes(key("title").value("Fields for answer revision")),
                    fieldWithPath("answerContent")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value("내용"))
                        .description("수정한 내용"))));
    }

    @DisplayName("답변 리스트 조회")
    @Test
    void getAnswerListTest() throws Exception {
        given(answerService.getAnswers(Mockito.anyLong()))
            .willReturn(List.of(new Answer(), new Answer()));
        given(mapper.answersToResponseDtos(Mockito.anyList()))
            .willReturn(List.of(response1, response2));

        ResultActions actions = mockMvc.perform(
            get(BASE_URL)
                .param("questionId", "1")
                .accept(MediaType.APPLICATION_JSON));

        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data.[0].questionId").exists())
            .andExpect(jsonPath("$.data.[0].memberId").exists())
            .andExpect(jsonPath("$.data.[0].answerContent").exists())
            .andExpect(jsonPath("$.data.[0].votes").exists())
            .andExpect(jsonPath("$.data.[0].state").exists())
            .andExpect(jsonPath("$.data.[1].questionId").exists())
            .andExpect(jsonPath("$.data.[1].memberId").exists())
            .andExpect(jsonPath("$.data.[1].answerContent").exists())
            .andExpect(jsonPath("$.data.[1].votes").exists())
            .andExpect(jsonPath("$.data.[1].state").exists())
            .andDo(document("Get-Answers",
                preprocessResponse(prettyPrint()),
                requestParameters(
                    parameterWithName("questionId").description("불러올 답변 리스트들의 질문")),
                responseFields(
                    fieldWithPath("data")
                        .type(JsonFieldType.ARRAY)
                        .description("조회 데이터"),
                    fieldWithPath("data[].answerId")
                        .type(JsonFieldType.NUMBER)
                        .description("답변 식별자"),
                    fieldWithPath("data[].questionId")
                        .type(JsonFieldType.NUMBER)
                        .description("글"),
                    fieldWithPath("data[].memberId")
                        .type(JsonFieldType.NUMBER)
                        .description("답변 작성자"),
                    fieldWithPath("data[].answerContent")
                        .type(JsonFieldType.STRING)
                        .description("답변 내용"),
                    fieldWithPath("data[].votes")
                        .type(JsonFieldType.NUMBER)
                        .description("받은 투표 현황"),
                    fieldWithPath("data[].state")
                        .type(JsonFieldType.STRING)
                        .description("답변 상태"),
                    fieldWithPath("data[].createdAt")
                        .type(JsonFieldType.NULL)
                        .description("질답 생성 시간"),
                    fieldWithPath("data[].modifiedAt")
                        .type(JsonFieldType.NULL)
                        .description("답변 수정 시간"))));

    }

    @DisplayName("답변 삭제")
    @Test
    @WithMockCustomMember
    void deleteAnswerTest() throws Exception {
        doNothing().when(answerService).deleteAnswer(answer.getAnswerId(), member.getMemberId());

        ResultActions actions = mockMvc.perform(
            delete(BASE_URL + "/{answer-id}", answer.getAnswerId())
                .with(csrf())
                .header("Authorization", "")
                .accept(MediaType.APPLICATION_JSON));

        actions
            .andExpect(status().isNoContent())
            .andExpect(jsonPath("$.data").doesNotExist())
            .andDo(document("Delete-Answer",
                pathParameters(
                    parameterWithName("answer-id").description("답변 아이디")
                ),
                requestHeaders(
                    attributes(key("title")
                        .value("Headers for user revision")),
                    headerWithName("Authorization")
                        .attributes(key("constraints").value("Bearer {accessToken}"))
                        .description("액세스 토큰")
                ))
            );
    }
}
