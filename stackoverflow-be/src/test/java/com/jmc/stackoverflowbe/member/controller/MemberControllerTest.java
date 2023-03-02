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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.jmc.stackoverflowbe.global.WithMockCustomMember;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(MemberController.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
@WithMockUser(username = "kimcoding@gmail.com", roles = {"USER"})
public class MemberControllerTest {

    String BASE_URL = "/members";

    Member member = Member.builder()
        .memberId(1L)
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
        .memberId(member.getMemberId())
        .email(member.getEmail())
        .name(member.getName())
        .state(member.getState())
        .isMine(false)
        .picture("https://lh3.googleusercontent.com/a/AGNmyxYZlOMhTobPqQ0YS4-IPQqfkaVkjEwWYC2fLUw=s96-c")
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

        // memberService.createMember()가 member를 반환.
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(member);

        // post를 body로 포함하여 post mock 요청.
        ResultActions actions = mockMvc.perform(
            post(BASE_URL)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        // 제약 조건
        ConstraintDescriptions postQuestionConstraints = new ConstraintDescriptions(
            MemberDto.Post.class);
        List<String> emailDescriptions = postQuestionConstraints
            .descriptionsForProperty("email");
        List<String> nameDescriptions = postQuestionConstraints
            .descriptionsForProperty("name");

        // 응답 검증 후 api 문서 스니펫 생성.
        actions
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", is(startsWith("/members/")))) // Location 헤더 검증.
            .andDo(document("Post-Member",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields( // 요청 body 필드
                    attributes(key("title")
                        .value("Fields for user creation")),
                    fieldWithPath("email")
                        .type(JsonFieldType.STRING) // 필드 타입
                        .attributes(key("constraints").value(
                            emailDescriptions)) // 필드
                        // 제약
                        // 조건
                        .description("회원 이메일"), // 필드 설명
                    fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value(
                            nameDescriptions))
                        .description("회원 이름")),
                responseHeaders( // 응답 헤더
                    headerWithName(HttpHeaders.LOCATION) // 헤더 이름
                        .description("Header Location, 리소스의 URL") // 헤더
                    // 설명
                )));
    }

    @DisplayName("회원 수정")
    @Test
    @WithMockCustomMember
    void patchMember() throws Exception {
        String content = gson.toJson(patch);

        // 변경 로직을 mock 처리 하기 때문에 위에서 수정.
        member.setName(patch.getName());
        member.setLocation(patch.getLocation());

        given(mapper.patchDtoToMember(Mockito.any(MemberDto.Patch.class))).willReturn(new Member());
        // patch를 Member객체로 매핑
        given(mapper.patchDtoToMember(Mockito.any(MemberDto.Patch.class))).willReturn(new Member());
        // memberService.updateMember()가 member를 반환.
        given(memberService.updateMember(Mockito.any(Member.class))).willReturn(member);

        // patch를 body에 포함하여 수정하려는 memberId를 path parameter로 patch 요청.
        ResultActions actions = mockMvc.perform(
            patch(BASE_URL + "/{member-id}", member.getMemberId())
                .with(csrf())
                .header("Authorization", "")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        // 제약 조건
        ConstraintDescriptions patchQuestionConstraints = new ConstraintDescriptions(
            MemberDto.Post.class);
        List<String> nameDescriptions = patchQuestionConstraints
            .descriptionsForProperty("name");
        List<String> locationDescriptions = patchQuestionConstraints
            .descriptionsForProperty("location");
        List<String> aboutDescriptions = patchQuestionConstraints
            .descriptionsForProperty("about");

        // // 응답 검증 후 api 문서 스니펫 생성.
        actions
            .andExpect(status().isOk())
            .andDo(document("Patch-Member",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters( // path parameter
                    parameterWithName("member-id") // parameter 이름
                        .description("회원 아이디")), // parameter 설명
                requestFields( // 요청 body 필드
                    attributes(key("title")
                        .value("Fields for user revision")),
                    fieldWithPath("name") // 필드 이름
                        .type(JsonFieldType.STRING) // 필드 타입
                        .attributes(key("constraints").value(
                            nameDescriptions)) // 필드
                        // 제약
                        // 조건
                        .optional() // 필드 필수 여부
                        .description("회원 이름"), // 필드 설명
                    fieldWithPath("location")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value(
                            locationDescriptions))
                        .optional()
                        .description("회원 활동 지역"),
                    fieldWithPath("about")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value(
                            aboutDescriptions))
                        .optional()
                        .description("회원 소개"))));
    }

    @DisplayName("회원 조회")
    @Test
    void getMember() throws Exception {
        // memberService.getMember()가 response를 반환
        given(memberService.getMember(Mockito.anyLong())).willReturn(member);

        given(mapper.memberToResponseDto(Mockito.any(Member.class))).willReturn(response);

        // 조회하려는 memberId를 path parameter로 get 요청
        ResultActions actions = mockMvc.perform(
            get(BASE_URL + "/{member-id}", member.getMemberId())
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // 응답 검증 후 api 문서 스니펫 생성.
        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.memberId").value(member.getMemberId())) // 응답으로 받은 memberId
            // 검증
            .andExpect(jsonPath("$.data.location").value(member.getLocation()))
            .andExpect(jsonPath("$.data.about").value(member.getAbout()))
            .andDo(document("Get-Member",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters( // path parameter
                    parameterWithName("member-id") // parameter 이름
                        .description("회원 아이디")), // parameter 설명
                responseFields( // 응답 필드
                    fieldWithPath("data") // 필드 이름
                        .type(JsonFieldType.OBJECT) // 필드 타입
                        .description("조회 데이터"), // 필드 설명
                    fieldWithPath("data.memberId")
                        .type(JsonFieldType.NUMBER)
                        .description("회원 아이디"),
                    fieldWithPath("data.email")
                        .type(JsonFieldType.STRING)
                        .description("회원 이메일"),
                    fieldWithPath("data.name")
                        .type(JsonFieldType.STRING)
                        .description("회원 이름"),
                    fieldWithPath("data.picture")
                        .type(JsonFieldType.STRING)
                        .description("회원 사진"),
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
                        .description("마지막 접속일"))));
    }

    @DisplayName("회원 삭제")
    @Test
    void deleteMember() throws Exception {
        // memberService.deleteMebmer()가 반환을 하지 않음.
        doNothing().when(memberService).deleteMember(member.getMemberId());

        // 삭제하려는 memberId를 path parameter로 delete 요청.
        ResultActions actions = mockMvc.perform(
            delete(BASE_URL + "/{member-id}", member.getMemberId())
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON));

        // 응답 검증 후 api 문서 스니펫 생성.
        actions
            .andExpect(status().isNoContent())
            .andExpect(jsonPath("$.data").doesNotExist()) // json 응답이 없음.
            .andDo(document("Delete-Member",
                pathParameters( // path parameter
                    parameterWithName("member-id").description("회원 아이디") // parameter
                    // 설명
                )));
    }
}
