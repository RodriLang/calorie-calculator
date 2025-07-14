package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.TagDto;
import com.trainerapp.calorie_calculator.dto.create.TagDataDto;
import com.trainerapp.calorie_calculator.model.entity.Tag;

public interface TagMapper {

    TagDto toDto(Tag tag);

    Tag fromDataDto(TagDataDto tagDataDto);

}
