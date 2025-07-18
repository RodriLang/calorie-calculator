package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.request.SectionRequestDto;
import com.trainerapp.calorie_calculator.dto.response.SectionResponseDto;
import com.trainerapp.calorie_calculator.model.entity.Section;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
uses = {IngredientMapper.class,
        SeasoningMapper.class,
        StepMapper.class})
public interface SectionMapper {

    SectionResponseDto toDto(Section section);

    Section toEntity(SectionRequestDto sectionRequestDto);

}
