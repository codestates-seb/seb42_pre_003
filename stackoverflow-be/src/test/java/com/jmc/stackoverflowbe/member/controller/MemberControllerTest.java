package com.jmc.stackoverflowbe.member.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
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
        .name("?????????")
        .state(MemberState.ACTIVE)
        .about("???????????????")
        .location("??????")
        .build();

    MemberDto.Post post = MemberDto.Post.builder()
        .email("hgd@gmail.com")
        .name("?????????")
        .build();

    MemberDto.Patch patch = MemberDto.Patch.builder()
        .name("?????????")
        .about("???????????????")
        .location("??????")
        .build();

    MemberDto.Response response = MemberDto.Response.builder()
        .memberId(member.getMemberId())
        .email(member.getEmail())
        .name(member.getName())
        .state(member.getState())
        .picture(
            "https://lh3.googleusercontent.com/a/AGNmyxYZlOMhTobPqQ0YS4-IPQqfkaVkjEwWYC2fLUw=s96-c")
        .about("???????????????")
        .location("??????")
        .build();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @MockBean
    MemberMapper mapper;

    @Autowired
    Gson gson;

    @DisplayName("?????? ??????")
    @Test
    void postMemberTest() throws Exception {
        String content = gson.toJson(post);

        // memberService.createMember()??? member??? ??????.
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(member);

        // post??? body??? ???????????? post mock ??????.
        ResultActions actions = mockMvc.perform(
            post(BASE_URL)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        // ?????? ??????
        ConstraintDescriptions postQuestionConstraints = new ConstraintDescriptions(
            MemberDto.Post.class);
        List<String> emailDescriptions = postQuestionConstraints
            .descriptionsForProperty("email");
        List<String> nameDescriptions = postQuestionConstraints
            .descriptionsForProperty("name");

        // ?????? ?????? ??? api ?????? ????????? ??????.
        actions
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", is(startsWith("/members/")))) // Location ?????? ??????.
            .andDo(document("Post-Member",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields( // ?????? body ??????
                    attributes(key("title")
                        .value("Fields for user creation")),
                    fieldWithPath("email")
                        .type(JsonFieldType.STRING) // ?????? ??????
                        .attributes(key("constraints").value(
                            emailDescriptions)) // ??????
                        // ??????
                        // ??????
                        .description("?????? ?????????"), // ?????? ??????
                    fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value(
                            nameDescriptions))
                        .description("?????? ??????")),
                responseHeaders( // ?????? ??????
                    headerWithName(HttpHeaders.LOCATION) // ?????? ??????
                        .description("Header Location, ???????????? URL") // ??????
                    // ??????
                )));
    }

    @DisplayName("?????? ??????")
    @Test
    @WithMockCustomMember
    void patchMember() throws Exception {
        String content = gson.toJson(patch);

        // ?????? ????????? mock ?????? ?????? ????????? ????????? ??????.
        member.setName(patch.getName());
        member.setLocation(patch.getLocation());

        given(mapper.patchDtoToMember(Mockito.any(MemberDto.Patch.class))).willReturn(new Member());
        // patch??? Member????????? ??????
        given(mapper.patchDtoToMember(Mockito.any(MemberDto.Patch.class))).willReturn(new Member());
        // memberService.updateMember()??? member??? ??????.
        given(memberService.updateMember(Mockito.any(Member.class))).willReturn(member);

        // patch??? body??? ???????????? ??????????????? memberId??? path parameter??? patch ??????.
        ResultActions actions = mockMvc.perform(
            patch(BASE_URL + "/{member-id}", member.getMemberId())
                .with(csrf())
                .header("Authorization", "")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content));

        // ?????? ??????
        ConstraintDescriptions patchQuestionConstraints = new ConstraintDescriptions(
            MemberDto.Post.class);
        List<String> nameDescriptions = patchQuestionConstraints
            .descriptionsForProperty("name");
        List<String> locationDescriptions = patchQuestionConstraints
            .descriptionsForProperty("location");
        List<String> aboutDescriptions = patchQuestionConstraints
            .descriptionsForProperty("about");

        // // ?????? ?????? ??? api ?????? ????????? ??????.
        actions
            .andExpect(status().isOk())
            .andDo(document("Patch-Member",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    attributes(key("title")
                        .value("Headers for user revision")),
                    headerWithName("Authorization")
                        .attributes(key("constraints").value("Berrer {accessToken}"))
                        .description("????????? ??????")
                ),
                pathParameters( // path parameter
                    parameterWithName("member-id") // parameter ??????
                        .description("?????? ?????????")), // parameter ??????
                requestFields( // ?????? body ??????
                    attributes(key("title")
                        .value("Fields for user revision")),
                    fieldWithPath("name") // ?????? ??????
                        .type(JsonFieldType.STRING) // ?????? ??????
                        .attributes(key("constraints").value(
                            nameDescriptions)) // ??????
                        // ??????
                        // ??????
                        .optional() // ?????? ?????? ??????
                        .description("?????? ??????"), // ?????? ??????
                    fieldWithPath("location")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value(
                            locationDescriptions))
                        .optional()
                        .description("?????? ?????? ??????"),
                    fieldWithPath("about")
                        .type(JsonFieldType.STRING)
                        .attributes(key("constraints").value(
                            aboutDescriptions))
                        .optional()
                        .description("?????? ??????"))));
    }

    @DisplayName("?????? ??????")
    @Test
    @WithMockCustomMember
    void getMember() throws Exception {
        // memberService.getMember()??? response??? ??????
        given(memberService.getMember(Mockito.anyLong())).willReturn(member);

        given(mapper.memberToResponseDto(Mockito.any(Member.class))).willReturn(response);

        // ??????????????? memberId??? path parameter??? get ??????
        ResultActions actions = mockMvc.perform(
            get(BASE_URL + "/{member-id}", member.getMemberId())
                .header("Authorization", "")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // ?????? ?????? ??? api ?????? ????????? ??????.
        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.memberId").value(member.getMemberId())) // ???????????? ?????? memberId
            // ??????
            .andExpect(jsonPath("$.data.location").value(member.getLocation()))
            .andExpect(jsonPath("$.data.about").value(member.getAbout()))
            .andDo(document("Get-Member",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    attributes(key("title")
                        .value("Headers for user revision")),
                    headerWithName("Authorization")
                        .attributes(key("constraints").value("Berrer {accessToken}"))
                        .optional()
                        .description("????????? ??????")
                ),
                pathParameters( // path parameter
                    parameterWithName("member-id") // parameter ??????
                        .description("?????? ?????????")), // parameter ??????
                responseFields( // ?????? ??????
                    fieldWithPath("data") // ?????? ??????
                        .type(JsonFieldType.OBJECT) // ?????? ??????
                        .description("?????? ?????????"), // ?????? ??????
                    fieldWithPath("data.memberId")
                        .type(JsonFieldType.NUMBER)
                        .description("?????? ?????????"),
                    fieldWithPath("data.email")
                        .type(JsonFieldType.STRING)
                        .description("?????? ?????????"),
                    fieldWithPath("data.name")
                        .type(JsonFieldType.STRING)
                        .description("?????? ??????"),
                    fieldWithPath("data.picture")
                        .type(JsonFieldType.STRING)
                        .description("?????? ??????"),
                    fieldWithPath("data.location")
                        .type(JsonFieldType.STRING)
                        .description("?????? ?????? ??????"),
                    fieldWithPath("data.about")
                        .type(JsonFieldType.STRING)
                        .description("?????? ??????"),
                    fieldWithPath("data.state")
                        .type(JsonFieldType.STRING)
                        .description("?????? ??????"),
                    fieldWithPath("data.isMine")
                        .type(JsonFieldType.BOOLEAN)
                        .description("?????? ??????"),
                    fieldWithPath("data.createdAt")
                        .type(JsonFieldType.NULL)
                        .description("?????????"),
                    fieldWithPath("data.modifiedAt")
                        .type(JsonFieldType.NULL)
                        .description("?????? ?????????"),
                    fieldWithPath("data.lastLoginTime")
                        .type(JsonFieldType.NULL)
                        .description("????????? ?????????"))));
    }

    @DisplayName("?????? ??????")
    @Test
    @WithMockCustomMember
    void deleteMember() throws Exception {
        // memberService.deleteMebmer()??? ????????? ?????? ??????.
        doNothing().when(memberService).deleteMember(member.getMemberId());

        // ??????????????? memberId??? path parameter??? delete ??????.
        ResultActions actions = mockMvc.perform(
            delete(BASE_URL + "/{member-id}", member.getMemberId())
                .header("Authorization", "")
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON));

        // ?????? ?????? ??? api ?????? ????????? ??????.
        actions
            .andExpect(status().isNoContent())
            .andExpect(jsonPath("$.data").doesNotExist()) // json ????????? ??????.
            .andDo(document("Delete-Member",
                preprocessRequest(prettyPrint()),
                requestHeaders(
                    attributes(key("title")
                        .value("Headers for user revision")),
                    headerWithName("Authorization")
                        .attributes(key("constraints").value("Berrer {accessToken}"))
                        .description("????????? ??????")
                ),
                pathParameters( // path parameter
                    parameterWithName("member-id").description("?????? ?????????") // parameter
                    // ??????
                )));
    }
}
