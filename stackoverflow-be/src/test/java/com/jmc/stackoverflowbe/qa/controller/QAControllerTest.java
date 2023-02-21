package com.jmc.stackoverflowbe.qa.controller;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.jmc.stackoverflowbe.article.entity.Article;
import com.jmc.stackoverflowbe.member.entity.Member;
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

@WebMvcTest(QAController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class QAControllerTest {
    String BASE_URL = "/qas";

    QA qa = QA.builder()
        .id(1L)
        .qaContent("testing content")
        .group(QaGroup.QUESTION)
        .state(StateGroup.ACTIVE)
        .votes(0L)
        .member(new Member())
        .article(new Article())
        .build();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    QAService qaService;

    @MockBean
    QAMapper mapper;

    @Autowired
    Gson gson;

    @DisplayName("Qa 생성")
    @Test
    void postQATest() throws Exception{
        QADto.Post post = QADto.Post.builder()
            .article(new Article())
            .member(new Member())
            .qaContent("post testing content")
            .votes(0)
            .build();

        String content = gson.toJson(post);
        given(mapper.PostDtoToQA(Mockito.any(QADto.Post.class))).willReturn(qa);
        given(qaService.createQA(Mockito.any(QA.class))).willReturn(qa);

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
                    fieldWithPath("article")
                        .type(JsonFieldType.OBJECT)
                        .attributes(key("constraints").value("글"))
                        .description("작성된 글"),
                    fieldWithPath("member")
                        .type(JsonFieldType.OBJECT)
                        .attributes(key("constraints").value("작성자"))
                        .description("질답 작성자"),
                    fieldWithPath("qaContent")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value("내용"))
                        .description("질답 내용"),
                    fieldWithPath("votes")
                        .type(JsonFieldType.NUMBER)
                        .attributes(key("constraints").value("투표"))
                        .description("받은 투표의 합계")),
                responseHeaders(
                    headerWithName(HttpHeaders.LOCATION)
                        .description("Header Location, 리소스의 URL")
                )));
    }

}
