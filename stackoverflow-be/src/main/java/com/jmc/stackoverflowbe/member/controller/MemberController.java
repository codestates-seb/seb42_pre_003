package com.jmc.stackoverflowbe.member.controller;

import com.jmc.stackoverflowbe.global.common.SingleResponseDto;
import com.jmc.stackoverflowbe.global.exception.BusinessLogicException;
import com.jmc.stackoverflowbe.global.exception.ExceptionCode;
import com.jmc.stackoverflowbe.global.security.auth.dto.LogInMemberDto;
import com.jmc.stackoverflowbe.global.security.auth.resolver.LoginMember;
import com.jmc.stackoverflowbe.global.utils.UriCreator;
import com.jmc.stackoverflowbe.member.dto.MemberDto;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.mapper.MemberMapper;
import com.jmc.stackoverflowbe.member.service.MemberService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;

    @PostMapping
    public ResponseEntity postMember(@RequestBody MemberDto.Post post) {
        memberService.createMember(mapper.postDtoToMember(post));

        // Location 헤더에 추가할 URI를 생성.
        URI location = UriCreator.createURI("/members", 1L);

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
        @LoginMember LogInMemberDto loginMember,
        @PathVariable("member-id") long memberId,
        @RequestBody MemberDto.Patch patch) {
        // 요청하는 리소스의 소유자와 요청한 사용자가 일치하는지 검증
        memberService.verifyResourceOwner(memberId, loginMember);

        Member member = mapper.patchDtoToMember(patch);
        member.setMemberId(memberId);

        memberService.updateMember(member);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity getMemberInfo(@LoginMember LogInMemberDto loginMember) {
        Member member = memberService.getMember(loginMember.getMemberId());

        return new ResponseEntity(
            new SingleResponseDto<>(mapper.memberToMeResponseDto(member)),
            HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
        @LoginMember LogInMemberDto loginMember,
        @PathVariable("member-id") long memberId) {

        Member member = memberService.getMember(memberId);
        MemberDto.Response response = mapper.memberToResponseDto(member);
        if(loginMember != null && memberService.isResourceOwner(member.getMemberId(), loginMember))
            response.setIsMine(true);

        return new ResponseEntity(
            new SingleResponseDto<>(response),
            HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
        @LoginMember LogInMemberDto loginMember,
        @PathVariable("member-id") long memberId) {
        memberService.verifyResourceOwner(memberId, loginMember);

        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }
}
