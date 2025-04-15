package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.MicronutrientContentDto;
import com.trainerapp.calorie_calculator.model.dto.create.MicronutrientContentDataDto;
import com.trainerapp.calorie_calculator.model.entity.MicronutrientContent;

public interface MicronutrientContentMapper {


    MicronutrientContentDto toDto(MicronutrientContent micronutrientContent);

    MicronutrientContent fromDataDto(MicronutrientContentDataDto micronutrientContentDataDto);
}