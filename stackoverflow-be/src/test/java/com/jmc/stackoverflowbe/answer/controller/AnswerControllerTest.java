package com.jmc.stackoverflowbe.answer.controller;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
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
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.jmc.stackoverflowbe.answer.dto.AnswerDto;
import com.jmc.stackoverflowbe.answer.entity.Answer;
import com.jmc.stackoverflowbe.answer.mapper.AnswerMapper;
import com.jmc.stackoverflowbe.answer.service.AnswerService;
import com.jmc.stackoverflowbe.qa.controller.QAController;
import com.jmc.stackoverflowbe.qa.dto.QADto;
import com.jmc.stackoverflowbe.qa.entity.QA;
import com.jmc.stackoverflowbe.qa.entity.QA.QaGroup;
import com.jmc.stackoverflowbe.qa.entity.QA.StateGroup;
import com.jmc.stackoverflowbe.qa.mapper.QAMapper;
import com.jmc.stackoverflowbe.qa.service.QAService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(AnswerController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class AnswerControllerTest {
    String BASE_URL = "/answers";

    Answer answer = Answer.builder()
        .answerId(1L)
        .answerContent("testing content")
        .state(Answer.StateGroup.ACTIVE)
        .votes(0L)
        .memberId(1L)
        .questionId(1L)
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
    void postAnswerTest() throws Exception{
        AnswerDto.Post post = AnswerDto.Post.builder()
            .questionId(0L)
            .memberId(0L)
            .answerContent("post testing content")
            .build();

        String content = gson.toJson(post);
        given(mapper.postDtoToAnswer(Mockito.any(AnswerDto.Post.class)))
            .willReturn(answer);
        given(answerService.createAnswer(Mockito.any(Answer.class)))
            .willReturn(answer);

        ResultActions actions = mockMvc.perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        actions
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", is(startsWith("/answers"))))
            .andDo(document("Post-Answer",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    attributes(key("title").value("Fields for answer creation")),
                    fieldWithPath("questionId")
                        .type(JsonFieldType.NUMBER)
                        .attributes(key("constraints").value("질문"))
                        .description("작성된 질문"),
                    fieldWithPath("memberId")
                        .type(JsonFieldType.NUMBER)
                        .attributes(key("constraints").value("작성자"))
                        .description("질답 작성자"),
                    fieldWithPath("answerContent")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value("내용"))
                        .description("답변 내용")),
                responseHeaders(
                    headerWithName(HttpHeaders.LOCATION)
                        .description("Header Location, 리소스의 URL")
                )));
    }

    @DisplayName("답변 수정")
    @Test
    void patchAnswerTest() throws Exception{
        AnswerDto.Patch patch = AnswerDto.Patch.builder()
            .answerContent("patch testing content")
            .build();

        String content = gson.toJson(patch);
        given(mapper.patchDtoToAnswer(Mockito.any(AnswerDto.Patch.class)))
            .willReturn(new Answer());
        given(answerService.updateAnswer(Mockito.any(Answer.class)))
            .willReturn(answer);

        ResultActions actions = mockMvc.perform(
            patch(BASE_URL+"/{answer-id}", answer.getAnswerId())
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
                requestFields(
                    attributes(key("title").value("Fields for answer revision")),
                    fieldWithPath("answerContent")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value("내용"))
                        .description("수정한 내용"))));
    }

    @DisplayName("질답 조회")
    @Test
    void getQATest() throws Exception{
        AnswerDto.Response response = AnswerDto.Response.builder()
            .answerId(1L)
            .questionId(1L)
            .memberId(0L)
            .answerContent("get testing content")
            .votes(0)
            .state(StateGroup.ACTIVE)
            .build();

        given(answerService.getAnswer(Mockito.anyLong()))
            .willReturn(new Answer());
        given(mapper.answerToResponseDto(Mockito.any(Answer.class)))
            .willReturn(response);

        ResultActions actions = mockMvc.perform(
            get(BASE_URL+"/{answer-id}", answer.getAnswerId())
                .accept(MediaType.APPLICATION_JSON));

        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.questionId").exists())
            .andExpect(jsonPath("$.data.memberId").exists())
            .andExpect(jsonPath("$.data.answerContent").exists())
            .andExpect(jsonPath("$.data.votes").exists())
            .andExpect(jsonPath("$.data.state").exists())
            .andDo(document("Get-Answer",
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("answer-id")
                        .description("질답 식별자")),
                responseFields(
                    fieldWithPath("data")
                        .type(JsonFieldType.OBJECT)
                        .description("조회 데이터"),
                    fieldWithPath("data.answerId")
                        .type(JsonFieldType.NUMBER)
                        .description("질답 식별자"),
                    fieldWithPath("data.questionId")
                        .type(JsonFieldType.NUMBER)
                        .description("글"),
                    fieldWithPath("data.memberId")
                        .type(JsonFieldType.NUMBER)
                        .description("질답 작성자"),
                    fieldWithPath("data.answerContent")
                        .type(JsonFieldType.STRING)
                        .description("질답 내용"),
                    fieldWithPath("data.votes")
                        .type(JsonFieldType.NUMBER)
                        .description("받은 투표 현황"),
                    fieldWithPath("data.state")
                        .type(JsonFieldType.STRING)
                        .description("질답 상태"),
                    fieldWithPath("data.createdAt")
                        .type(JsonFieldType.NULL)
                        .description("질답 생성 시간"),
                    fieldWithPath("data.modifiedAt")
                        .type(JsonFieldType.NULL)
                        .description("질답 수정 시간"))));

    }

    @DisplayName("답변 삭제")
    @Test
    void deleteAnswerTest() throws Exception{
        doNothing().when(answerService).deleteAnswer(answer.getAnswerId());

        ResultActions actions = mockMvc.perform(
            delete(BASE_URL + "/{answer-id}", answer.getAnswerId())
                .accept(MediaType.APPLICATION_JSON));

        actions
            .andExpect(status().isNoContent())
            .andExpect(jsonPath("$.data").doesNotExist())
            .andDo(document("Delete-Answer",
                pathParameters(
                    parameterWithName("answer-id").description("답변 아이디")
                ))
            );
    }
}
