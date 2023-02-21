package com.jmc.stackoverflowbe.member.mapper;

import com.jmc.stackoverflowbe.member.dto.MemberDto;
import com.jmc.stackoverflowbe.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member PostDtoToMember(MemberDto.Post post);

    Member PatchDtoToMember(MemberDto.Patch patch);

    MemberDto.Response memberToResponseDto(Member member);
}
