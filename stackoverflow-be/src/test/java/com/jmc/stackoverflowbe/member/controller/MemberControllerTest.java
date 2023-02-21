package com.jmc.stackoverflowbe.member.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.jmc.stackoverflowbe.global.common.SingleResponseDto;
import com.jmc.stackoverflowbe.member.dto.MemberDto;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import com.jmc.stackoverflowbe.member.mapper.MemberMapper;
import com.jmc.stackoverflowbe.member.service.MemberService;
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

@WebMvcTest(MemberController.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
public class MemberControllerTest {
    String BASE_URL = "/members";

    Member member = Member.builder()
        .id(1L)
        .email("hgd@gmail.com")
        .name("홍길동")
        .state(MemberState.ACTIVE)
        .build();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @MockBean
    MemberMapper mapper;

    @Autowired
    Gson gson;
    @DisplayName("회원 생성")
    @Test
    void postMemberTest() throws Exception {
        MemberDto.Post post = MemberDto.Post.builder()
            .email("hgd@gmail.com")
            .name("홍길동")
            .build();

        String content = gson.toJson(post);

        given(mapper.PostDtoToMember(Mockito.any(MemberDto.Post.class))).willReturn(member);
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(member);

        ResultActions actions = mockMvc.perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        actions
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", is(startsWith("/members/"))))
            .andDo(document("Post-Member",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    attributes(key("title").value("Fields for user creation")),
                    fieldWithPath("email")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value("이메일"))
                        .description("회원 이메일"),
                    fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value("이름"))
                        .description("회원 이름")),
                responseHeaders(
                    headerWithName(HttpHeaders.LOCATION)
                        .description("Header Location, 리소스의 URL")
                )));
    }

    @DisplayName("회원 수정")
    @Test
    void patchMember() throws Exception {
        MemberDto.Patch patch = MemberDto.Patch.builder()
            .name("김코딩")
            .build();

        String content = gson.toJson(patch);

        member.setName(patch.getName());

        given(mapper.PostDtoToMember(Mockito.any(MemberDto.Post.class))).willReturn(member);
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(member);

        ResultActions actions = mockMvc.perform(
            patch(BASE_URL + "/{member-id}", member.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        actions
            .andExpect(status().isOk())
            .andDo(document("Patch-Member",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("member-id")
                        .description("회원 아이디")),
                requestFields(
                    attributes(key("title").value("Fields for user creation")),
                    fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value("이름"))
                        .optional()
                        .description("회원 이름"))
                ));
    }

    @DisplayName("회원 조회")
    @Test
    void getMember() throws Exception {
        MemberDto.Response response = MemberDto.Response.builder()
            .id(member.getId())
            .email(member.getEmail())
            .name(member.getName())
            .state(member.getState())
            .isMine(false)
            .build();

        given(memberService.getMember(Mockito.anyLong())).willReturn(member);
        given(mapper.memberToResponseDto(Mockito.any(Member.class))).willReturn(response);

        ResultActions actions = mockMvc.perform(
            get(BASE_URL + "/{member-id}", member.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        actions
            .andExpect(status().isOk())
            .andDo(document("Get-Member",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("member-id")
                        .description("회원 아이디")),
                responseFields(
                    fieldWithPath("data")
                        .type(JsonFieldType.OBJECT)
                        .description("조회 데이터"),
                    fieldWithPath("data.id")
                        .type(JsonFieldType.NUMBER)
                        .description("회원 아이디"),
                    fieldWithPath("data.email")
                        .type(JsonFieldType.STRING)
                        .description("회원 이매알"),
                    fieldWithPath("data.name")
                        .type(JsonFieldType.STRING)
                        .description("회원 이름"),
                    fieldWithPath("data.state")
                        .type(JsonFieldType.STRING)
                        .description("회원 상태"),
                    fieldWithPath("data.isMine")
                        .type(JsonFieldType.BOOLEAN)
                        .description("본인 확인"),
                    fieldWithPath("data.createdAt")
                        .type(JsonFieldType.NULL)
                        .description("가입일"),
                    fieldWithPath("data.modifiedAt")
                        .type(JsonFieldType.NULL)
                        .description("최근 수정일"),
                    fieldWithPath("data.lastLoginTime")
                        .type(JsonFieldType.NULL)
                        .description("마지막 접속일"))
            ));
    }
}
