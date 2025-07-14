package com.trainerapp.calorie_calculator.mapper;


import com.trainerapp.calorie_calculator.enums.NutrientType;
import com.trainerapp.calorie_calculator.dto.NutrientValueDto;

public interface NutrientValueMapper {

    NutrientValueDto map(Double value, NutrientType nutrientType);

}

