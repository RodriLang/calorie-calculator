package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.mapper.TagMapper;
import com.trainerapp.calorie_calculator.model.dto.TagDto;
import com.trainerapp.calorie_calculator.model.dto.create.TagDataDto;
import com.trainerapp.calorie_calculator.model.entity.Tag;

import org.springframework.stereotype.Component;

@Component
public class TagMapperImplementation implements TagMapper {

    public TagDto toDto(Tag tag) {
        return TagDto.builder()
                .id(tag.getId())
                .label(tag.getLabel())
                .build();
    }

    public Tag fromDataDto(TagDataDto tagDataDto) {
        return Tag.builder()
                .label(tagDataDto.label())
                .tagType(tagDataDto.tagType())
                .build();
    }

}
