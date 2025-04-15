package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.MicronutrientDto;
import com.trainerapp.calorie_calculator.model.dto.create.MicronutrientDataDto;
import com.trainerapp.calorie_calculator.model.entity.Micronutrient;

public interface MicronutrientMapper {

    Micronutrient fromDto(MicronutrientDto micronutrientDto);

    MicronutrientDto toDto(Micronutrient micronutrient);

    Micronutrient fromDataDto(MicronutrientDataDto micronutrientDataDto);
}
