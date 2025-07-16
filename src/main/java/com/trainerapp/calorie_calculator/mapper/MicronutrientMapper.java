package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.response.MicronutrientResponseDto;
import com.trainerapp.calorie_calculator.dto.request.MicronutrientRequestDto;
import com.trainerapp.calorie_calculator.model.entity.Micronutrient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MicronutrientMapper {

    Micronutrient fromDto(MicronutrientResponseDto micronutrientResponseDto);

    MicronutrientResponseDto toDto(Micronutrient micronutrient);

    Micronutrient fromDataDto(MicronutrientRequestDto micronutrientRequestDto);
}
