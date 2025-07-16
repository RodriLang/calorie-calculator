package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.response.TagResponseDto;
import com.trainerapp.calorie_calculator.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.model.entity.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagResponseDto toDto(Tag tag);

    Tag fromDataDto(TagRequestDto tagRequestDto);

}
