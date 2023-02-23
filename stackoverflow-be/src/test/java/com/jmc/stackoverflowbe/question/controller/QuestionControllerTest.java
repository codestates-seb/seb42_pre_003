package com.jmc.stackoverflowbe.question.controller;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
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
import com.jmc.stackoverflowbe.question.dto.QuestionDto;
import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.entity.Question.QaGroup;
import com.jmc.stackoverflowbe.question.entity.Question.StateGroup;
import com.jmc.stackoverflowbe.question.mapper.QuestionMapper;
import com.jmc.stackoverflowbe.question.service.QuestionService;
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

@WebMvcTest(QuestionController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class QuestionControllerTest {
    String BASE_URL = "/qas";

    Question question = Question.builder()
        .id(1L)
        .qaContent("testing content")
        .qaGroup(QaGroup.QUESTION)
        .state(StateGroup.ACTIVE)
        .votes(0L)
        .memberId(0L)
        .articleId(0L)
        .build();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuestionService questionService;

    @MockBean
    QuestionMapper mapper;

    @Autowired
    Gson gson;

    @DisplayName("Qa 생성")
    @Test
    void postQATest() throws Exception{
        QuestionDto.Post post = QuestionDto.Post.builder()
            .articleId(0L)
            .memberId(0L)
            .qaContent("post testing content")
            .build();

        String content = gson.toJson(post);
        given(mapper.PostDtoToQA(Mockito.any(QuestionDto.Post.class)))
            .willReturn(question);
        given(questionService.createQA(Mockito.any(Question.class)))
            .willReturn(question);

        ResultActions actions = mockMvc.perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        actions
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", is(startsWith("/qas/"))))
            .andDo(document("Post-QA",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    attributes(key("title").value("Fields for qa creation")),
                    fieldWithPath("articleId")
                        .type(JsonFieldType.NUMBER)
                        .attributes(key("constraints").value("글"))
                        .description("작성된 글"),
                    fieldWithPath("memberId")
                        .type(JsonFieldType.NUMBER)
                        .attributes(key("constraints").value("작성자"))
                        .description("질답 작성자"),
                    fieldWithPath("qaContent")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value("내용"))
                        .description("질답 내용")),
                responseHeaders(
                    headerWithName(HttpHeaders.LOCATION)
                        .description("Header Location, 리소스의 URL")
                )));
    }

    @DisplayName("질답 수정")
    @Test
    void patchQATest() throws Exception{
        QuestionDto.Patch patch = QuestionDto.Patch.builder()
            .qaId(1L)
            .qaContent("patch testing content")
            .votes(0)
            .build();

        String content = gson.toJson(patch);
        given(mapper.PatchDtoToQA(Mockito.any(QuestionDto.Patch.class)))
            .willReturn(new Question());
        given(questionService.updateQA(Mockito.any(Question.class)))
            .willReturn(question);

        ResultActions actions = mockMvc.perform(
            patch(BASE_URL+"/{qa-id}", patch.getQaId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        actions
            .andExpect(status().isOk())
            .andDo(document("Patch-QA",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    attributes(key("title").value("Fields for qa revision")),
                    fieldWithPath("qaId")
                        .type(JsonFieldType.NUMBER)
                        .attributes(key("constraints").value("질답 식별자"))
                        .description("질답 식별자"),
                    fieldWithPath("qaContent")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value("내용"))
                        .description("수정한 내용"),
                    fieldWithPath("votes")
                        .type(JsonFieldType.NUMBER)
                        .attributes(key("constraints").value("투표"))
                        .description("투표의 증감"))));
    }

    @DisplayName("질답 조회")
    @Test
    void getQATest() throws Exception{
        QuestionDto.Response response = QuestionDto.Response.builder()
            .id(question.getId())
            .articleId(0L)
            .memberId(0L)
            .qaContent("get testing content")
            .votes(0)
            .group(QaGroup.QUESTION)
            .state(StateGroup.ACTIVE)
            .build();

        given(questionService.getQA(Mockito.anyLong()))
            .willReturn(new Question());
        given(mapper.QAToResponseDto(Mockito.any(Question.class)))
            .willReturn(response);

        ResultActions actions = mockMvc.perform(
            get(BASE_URL+"/{qa-id}", question.getId())
                .accept(MediaType.APPLICATION_JSON));

        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.articleId").exists())
            .andExpect(jsonPath("$.data.memberId").exists())
            .andExpect(jsonPath("$.data.qaContent").exists())
            .andExpect(jsonPath("$.data.votes").exists())
            .andExpect(jsonPath("$.data.group").exists())
            .andExpect(jsonPath("$.data.state").exists())
            .andDo(document("Get-QA",
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("qa-id")
                        .description("질답 식별자")),
                responseFields(
                    fieldWithPath("data")
                        .type(JsonFieldType.OBJECT)
                        .description("조회 데이터"),
                    fieldWithPath("data.id")
                        .type(JsonFieldType.NUMBER)
                        .description("질답 식별자"),
                    fieldWithPath("data.articleId")
                        .type(JsonFieldType.NUMBER)
                        .description("글"),
                    fieldWithPath("data.memberId")
                        .type(JsonFieldType.NUMBER)
                        .description("질답 작성자"),
                    fieldWithPath("data.qaContent")
                        .type(JsonFieldType.STRING)
                        .description("질답 내용"),
                    fieldWithPath("data.votes")
                        .type(JsonFieldType.NUMBER)
                        .description("받은 투표 현황"),
                    fieldWithPath("data.group")
                        .type(JsonFieldType.STRING)
                        .description("질답 구분"),
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

    @DisplayName("질답 삭제")
    @Test
    void deleteQATest() throws Exception{
        doNothing().when(questionService).deleteQA(question.getId());

        ResultActions actions = mockMvc.perform(
            delete(BASE_URL + "/{qa-id}", question.getId())
                .accept(MediaType.APPLICATION_JSON));

        actions
            .andExpect(status().isNoContent())
            .andExpect(jsonPath("$.data").doesNotExist())
            .andDo(document("Delete-QA",
                pathParameters(
                    parameterWithName("qa-id").description("질답 아이디")
                ))
            );
    }

}
