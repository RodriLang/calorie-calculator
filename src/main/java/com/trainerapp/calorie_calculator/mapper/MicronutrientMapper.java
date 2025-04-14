package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.TagDto;
import com.trainerapp.calorie_calculator.model.entity.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    Tag tagDtoToTag(TagDto tagDto);

    TagDto tagToTagDto(Tag tag);
}
