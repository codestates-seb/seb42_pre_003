package com.jmc.stackoverflowbe.qa.mapper;

import com.jmc.stackoverflowbe.qa.dto.QADto;
import com.jmc.stackoverflowbe.qa.entity.QA;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QAMapper {

    QA PostDtoToQA(QADto.Post post);
    QA PatchDtoToQA(QADto.Patch patch);
    QADto.Response QAToResponseDto(QA qa);

}
