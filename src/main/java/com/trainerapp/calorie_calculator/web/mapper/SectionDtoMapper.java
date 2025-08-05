package com.trainerapp.calorie_calculator.web.mapper;

import com.trainerapp.calorie_calculator.domain.model.Section;
import com.trainerapp.calorie_calculator.web.dto.request.SectionRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.SectionResponseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        uses = {IngredientDtoMapper.class,
                SeasoningDtoMapper.class,
                StepDtoMapper.class})
public interface SectionDtoMapper {

    SectionResponseDto toDto(Section model);

    Section toModel(SectionRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Section updateFromDto(SectionRequestDto dto, @MappingTarget Section.SectionBuilder builder);

}
