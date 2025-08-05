package com.trainerapp.calorie_calculator.web.mapper;

import com.trainerapp.calorie_calculator.domain.model.Tag;
import com.trainerapp.calorie_calculator.web.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.TagResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagDtoMapper {

    TagResponseDto toDto(Tag model);

    Tag toModel(TagRequestDto dto);

}
