package com.jmc.stackoverflowbe.member.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.jmc.stackoverflowbe.member.dto.MemberDto;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import com.jmc.stackoverflowbe.member.mapper.MemberMapper;
import com.jmc.stackoverflowbe.member.service.MemberService;
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
        .about("안녕하세요")
        .location("서울")
        .build();

    MemberDto.Post post = MemberDto.Post.builder()
        .email("hgd@gmail.com")
        .name("홍길동")
        .build();

    MemberDto.Patch patch = MemberDto.Patch.builder()
        .name("김코딩")
        .about("안녕하세요")
        .location("서울")
        .build();

    MemberDto.Response response = MemberDto.Response.builder()
        .id(member.getId())
        .email(member.getEmail())
        .name(member.getName())
        .state(member.getState())
        .isMine(false)
        .about("안녕하세요")
        .location("서울")
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
        String content = gson.toJson(post);

        given(mapper.PostDtoToMember(Mockito.any(MemberDto.Post.class))).willReturn(new Member());
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(member);

        ResultActions actions = mockMvc.perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        ConstraintDescriptions postQuestionConstraints =
            new ConstraintDescriptions(MemberDto.Post.class);
        List<String> emailDescriptions = postQuestionConstraints
            .descriptionsForProperty("email");
        List<String> nameDescriptions = postQuestionConstraints
            .descriptionsForProperty("name");

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
                        .attributes(key("constraints").value(emailDescriptions))
                        .description("회원 이메일"),
                    fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value(nameDescriptions))
                        .description("회원 이름")),
                responseHeaders(
                    headerWithName(HttpHeaders.LOCATION)
                        .description("Header Location, 리소스의 URL")
                )
            ));
    }

    @DisplayName("회원 수정")
    @Test
    void patchMember() throws Exception {
        String content = gson.toJson(patch);

        member.setName(patch.getName());
        member.setLocation(patch.getLocation());

        given(mapper.PostDtoToMember(Mockito.any(MemberDto.Post.class))).willReturn(new Member());
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(member);

        ResultActions actions = mockMvc.perform(
            patch(BASE_URL + "/{member-id}", member.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        ConstraintDescriptions patchQuestionConstraints =
            new ConstraintDescriptions(MemberDto.Post.class);
        List<String> nameDescriptions = patchQuestionConstraints
            .descriptionsForProperty("name");
        List<String> locationDescriptions = patchQuestionConstraints
            .descriptionsForProperty("location");
        List<String> aboutDescriptions = patchQuestionConstraints
            .descriptionsForProperty("about");

        actions
            .andExpect(status().isOk())
            .andDo(document("Patch-Member",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("member-id")
                        .description("회원 아이디")),
                requestFields(
                    attributes(key("title").value("Fields for user revision")),
                    fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value(nameDescriptions))
                        .optional()
                        .description("회원 이름"),
                    fieldWithPath("location")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value(locationDescriptions))
                        .optional()
                        .description("회원 활동 지역"),
                    fieldWithPath("about")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value(aboutDescriptions))
                        .optional()
                        .description("회원 소개")
                )
            ));
    }

    @DisplayName("회원 조회")
    @Test
    void getMember() throws Exception {
        given(memberService.getMember(Mockito.anyLong())).willReturn(new Member());
        given(mapper.memberToResponseDto(Mockito.any(Member.class))).willReturn(response);

        ResultActions actions = mockMvc.perform(
            get(BASE_URL + "/{member-id}", member.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.id").value(member.getId()))
            .andExpect(jsonPath("$.data.location").value(member.getLocation()))
            .andExpect(jsonPath("$.data.about").value(member.getAbout()))
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
                        .description("회원 이매일"),
                    fieldWithPath("data.name")
                        .type(JsonFieldType.STRING)
                        .description("회원 이름"),
                    fieldWithPath("data.location")
                        .type(JsonFieldType.STRING)
                        .description("회원 활동 지역"),
                    fieldWithPath("data.about")
                        .type(JsonFieldType.STRING)
                        .description("회원 소개"),
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

    @DisplayName("회원 삭제")
    @Test
    void deleteMember() throws Exception {
        doNothing().when(memberService).deleteMember(member.getId());

        ResultActions actions = mockMvc.perform(
            delete(BASE_URL + "/{member-id}", member.getId())
                .accept(MediaType.APPLICATION_JSON));

        actions
            .andExpect(status().isNoContent())
            .andExpect(jsonPath("$.data").doesNotExist())
            .andDo(document("Delete-Member",
                pathParameters(
                    parameterWithName("member-id").description("회원 아이디")
                ))
            );
    }
}
