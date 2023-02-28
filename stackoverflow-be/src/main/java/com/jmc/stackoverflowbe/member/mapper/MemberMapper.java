package com.jmc.stackoverflowbe.member.mapper;

import com.jmc.stackoverflowbe.member.dto.MemberDto;
import com.jmc.stackoverflowbe.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member postDtoToMember(MemberDto.Post post);

    Member patchDtoToMember(MemberDto.Patch patch);

    MemberDto.Response memberToResponseDto(Member member);

    MemberDto.meResponse memberToMeResponseDto(Member member);
}
