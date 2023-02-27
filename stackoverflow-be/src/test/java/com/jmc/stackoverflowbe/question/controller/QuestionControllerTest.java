package com.jmc.stackoverflowbe.question.controller;


import static org.hamcrest.Matchers.any;
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
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.jmc.stackoverflowbe.question.dto.QuestionDto;
import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.entity.Question.StateGroup;
import com.jmc.stackoverflowbe.question.mapper.QuestionMapper;
import com.jmc.stackoverflowbe.question.service.QuestionService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    String BASE_URL = "/questions";

    Question question = Question.builder()
        .questionId(0L)
        .questionTitle("Question1 title for test")
        .memberId(0L)
        .questionContent("Question1 contents for test")
        .state(StateGroup.ACTIVE)
        .votes(0)
        .selection(false)
        .answers(0L)
        .views(0L)
        .build();
    Question question2 = Question.builder()
        .questionId(1L)
        .questionTitle("Question2 title for test")
        .memberId(1L)
        .questionContent("Question2 contents for test")
        .state(StateGroup.ACTIVE)
        .votes(1)
        .selection(false)
        .answers(1L)
        .views(1L)
        .build();
    QuestionDto.Response response = QuestionDto.Response.builder()
        .questionId(0L)
        .questionTitle("title for get")
        .questionContent("content for get")
        .memberId(0L)
        .state(StateGroup.ACTIVE)
        .votes(0)
        .selection(true)
        .answers(1L)
        .views(1L)
        .build();

    QuestionDto.Response response2 = QuestionDto.Response.builder()
        .questionId(0L)
        .questionTitle("title for get")
        .questionContent("content for get")
        .memberId(0L)
        .state(StateGroup.ACTIVE)
        .votes(0)
        .selection(true)
        .answers(1L)
        .views(1L)
        .build();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuestionService questionService;

    @MockBean
    QuestionMapper mapper;

    @Autowired
    Gson gson;

    @DisplayName("질문 생성")
    @Test
    void postQuestionTest() throws Exception{
        QuestionDto.Post post = QuestionDto.Post.builder()
            .questionTitle("Title for post")
            .questionContent("Contents for post")
            .build();

        String content = gson.toJson(post);
        given(mapper.postDtoToQuestion(Mockito.any(QuestionDto.Post.class)))
            .willReturn(question);
        given(questionService.createQuestion(Mockito.any(QuestionDto.Post.class)))
            .willReturn(question);

        ResultActions actions = mockMvc.perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        actions
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", is(startsWith("/questions/"))))
            .andDo(document("Post-Question",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    attributes(key("title").value("Fields for question creation")),
                    fieldWithPath("questionTitle")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value("제목"))
                        .description("질문 제목"),
                    fieldWithPath("questionContent")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value("내용"))
                        .description("질문 내용")),
                responseHeaders(
                    headerWithName(HttpHeaders.LOCATION)
                        .description("Header Location, 리소스의 URL")
                )));
    }

    @DisplayName("질문 수정")
    @Test
    void patchQuestionTest() throws Exception{
        QuestionDto.Patch patch = QuestionDto.Patch.builder()
            .questionTitle("title for patch")
            .questionContent("contents for patch")
            .build();

        String content = gson.toJson(patch);
        given(mapper.patchDtoToQuestion(Mockito.any(QuestionDto.Patch.class)))
            .willReturn(new Question());
        given(questionService.updateQuestion(Mockito.any(QuestionDto.Patch.class), Mockito.anyLong()))
            .willReturn(question);

        ResultActions actions = mockMvc.perform(
            patch(BASE_URL+"/{question-id}", 0L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        actions
            .andExpect(status().isOk())
            .andDo(document("Patch-Question",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("question-id")
                        .description("질문 식별자")),
                requestFields(
                    attributes(key("title").value("Fields for Question revision")),
                    fieldWithPath("questionTitle")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value("제목"))
                        .description("수정한 제목"),
                    fieldWithPath("questionContent")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value("내용"))
                        .description("수정한 내용"))));
    }

    @DisplayName("질문 상세 조회")
    @Test
    void getQuestionTest() throws Exception{

        given(questionService.getQuestion(Mockito.anyLong()))
            .willReturn(response);
        given(mapper.questionToResponseDto(Mockito.any(Question.class)))
            .willReturn(response);

        ResultActions actions = mockMvc.perform(
            get(BASE_URL+"/{question-id}", question.getQuestionId())
                .accept(MediaType.APPLICATION_JSON));

        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.questionId").exists())
            .andExpect(jsonPath("$.data.memberId").exists())
            .andExpect(jsonPath("$.data.questionTitle").exists())
            .andExpect(jsonPath("$.data.questionContent").exists())
            .andExpect(jsonPath("$.data.answers").exists())
            .andExpect(jsonPath("$.data.views").exists())
            .andExpect(jsonPath("$.data.votes").exists())
            .andExpect(jsonPath("$.data.state").exists())
            .andExpect(jsonPath("$.data.selection").exists())
            .andDo(document("Get-Question",
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("question-id")
                        .description("질문 식별자")),
                responseFields(
                    fieldWithPath("data")
                        .type(JsonFieldType.OBJECT)
                        .description("조회 데이터"),
                    fieldWithPath("data.questionId")
                        .type(JsonFieldType.NUMBER)
                        .description("질문 식별자"),
                    fieldWithPath("data.questionTitle")
                        .type(JsonFieldType.STRING)
                        .description("질문 제목"),
                    fieldWithPath("data.questionContent")
                        .type(JsonFieldType.STRING)
                        .description("질문 내용"),
                    fieldWithPath("data.memberId")
                        .type(JsonFieldType.NUMBER)
                        .description("질문 작성자"),
                    fieldWithPath("data.state")
                        .type(JsonFieldType.STRING)
                        .description("질문 상태"),
                    fieldWithPath("data.votes")
                        .type(JsonFieldType.NUMBER)
                        .description("질문 득표수"),
                    fieldWithPath("data.selection")
                        .type(JsonFieldType.BOOLEAN)
                        .description("채택 여부"),
                    fieldWithPath("data.answers")
                        .type(JsonFieldType.NUMBER)
                        .description("답변 갯수"),
                    fieldWithPath("data.views")
                        .type(JsonFieldType.NUMBER)
                        .description("조회수"),
                    fieldWithPath("data.createdAt")
                        .type(JsonFieldType.NULL)
                        .description("질문 생성 시간"),
                    fieldWithPath("data.modifiedAt")
                        .type(JsonFieldType.NULL)
                        .description("질문 수정 시간"))));

    }
    @DisplayName("질문 리스트 조회")
    @Test
    void getQuestionsTest() throws Exception{
        String page = "1";
        String sort = "questionId";
        List<QuestionDto.Response> questionResponses =
            List.of(response,response2);
        Page<QuestionDto.Response> responsePage = new PageImpl<>(questionResponses,
            PageRequest.of(0,15, Sort.by(sort).descending()), 2);

        given(mapper.questionsToQuestionResponses(Mockito.any(List.class)))
            .willReturn(questionResponses);
        given(questionService.getQuestions(Mockito.any(Integer.class),Mockito.any(String.class)))
            .willReturn(responsePage);

        ResultActions actions = mockMvc.perform(
            get(BASE_URL)
                .param("page", String.valueOf(page))
                .param("sort", sort)
                .accept(MediaType.APPLICATION_JSON));

        actions
            .andExpect(jsonPath("$.data[0]").exists())
            .andExpect(jsonPath("$.data[0].questionId").exists())
            .andExpect(jsonPath("$.data[0].memberId").exists())
            .andExpect(jsonPath("$.data[0].questionTitle").exists())
            .andExpect(jsonPath("$.data[0].questionContent").exists())
            .andExpect(jsonPath("$.data[0].answers").exists())
            .andExpect(jsonPath("$.data[0].views").exists())
            .andExpect(jsonPath("$.data[0].votes").exists())
            .andExpect(jsonPath("$.data[0].state").exists())
            .andExpect(jsonPath("$.data[0].selection").exists())
            .andDo(document(
                "Get-Questions",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestParameters(
                    List.of(
                        parameterWithName("page").description("페이지 번호"),
                        parameterWithName("sort").description("정렬 방식")
                    )
                ),
                responseFields(
                    List.of(
                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보 데이터"),
                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지 번호"),
                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 당 질문 수"),
                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 데이터 개수"),
                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 개수"),

                        fieldWithPath("data").type(JsonFieldType.ARRAY)
                            .description("페이지 결과 데이터"),
                        fieldWithPath("data[].questionId")
                            .type(JsonFieldType.NUMBER)
                            .description("질문 식별자"),
                        fieldWithPath("data[].questionTitle")
                            .type(JsonFieldType.STRING)
                            .description("질문 제목"),
                        fieldWithPath("data[].questionContent")
                            .type(JsonFieldType.STRING)
                            .description("질문 내용"),
                        fieldWithPath("data[].memberId")
                            .type(JsonFieldType.NUMBER)
                            .description("질문 작성자"),
                        fieldWithPath("data[].state")
                            .type(JsonFieldType.STRING)
                            .description("질문 상태"),
                        fieldWithPath("data[].votes")
                            .type(JsonFieldType.NUMBER)
                            .description("질문 득표수"),
                        fieldWithPath("data[].selection")
                            .type(JsonFieldType.BOOLEAN)
                            .description("채택 여부"),
                        fieldWithPath("data[].answers")
                            .type(JsonFieldType.NUMBER)
                            .description("답변 갯수"),
                        fieldWithPath("data[].views")
                            .type(JsonFieldType.NUMBER)
                            .description("조회수"),
                        fieldWithPath("data[].createdAt")
                            .type(JsonFieldType.NULL)
                            .description("질문 생성 시간"),
                        fieldWithPath("data[].modifiedAt")
                            .type(JsonFieldType.NULL)
                            .description("질문 수정 시간")))));


    }


    @DisplayName("질문 삭제")
    @Test
    void deleteQuestionTest() throws Exception{
        doNothing().when(questionService).deleteQuestion(question.getQuestionId());

        ResultActions actions = mockMvc.perform(
            delete(BASE_URL + "/{question-id}", question.getQuestionId())
                .accept(MediaType.APPLICATION_JSON));

        actions
            .andExpect(status().isNoContent())
            .andExpect(jsonPath("$.data").doesNotExist())
            .andDo(document("Delete-Question",
                pathParameters(
                    parameterWithName("question-id").description("질문 식별자")
                ))
            );
    }

}
