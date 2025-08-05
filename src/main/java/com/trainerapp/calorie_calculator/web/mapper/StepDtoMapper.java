package com.trainerapp.calorie_calculator.web.mapper;

import com.trainerapp.calorie_calculator.domain.model.Step;
import com.trainerapp.calorie_calculator.web.dto.request.StepRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.StepResponseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")

public interface StepDtoMapper {

    StepResponseDto toDto(Step model);

    Step toModel(StepRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Step updateFromDto(StepRequestDto dto, @MappingTarget Step.StepBuilder builder);

}
