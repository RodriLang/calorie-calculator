package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.request.RecipeRequestDto;
import com.trainerapp.calorie_calculator.dto.request.StepRequestDto;
import com.trainerapp.calorie_calculator.dto.response.StepResponseDto;
import com.trainerapp.calorie_calculator.model.entity.Recipe;
import com.trainerapp.calorie_calculator.model.entity.Step;
import org.mapstruct.*;

@Mapper(componentModel = "spring")

public interface StepMapper {

    StepResponseDto toDto(Step step);

    Step toEntity(StepRequestDto stepRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStepFromDto(StepRequestDto dto, @MappingTarget Step step);

}
