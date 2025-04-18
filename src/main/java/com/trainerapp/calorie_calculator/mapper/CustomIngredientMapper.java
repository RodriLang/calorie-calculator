package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.CustomIngredientDto;
import com.trainerapp.calorie_calculator.model.dto.create.CustomIngredientDataDto;
import com.trainerapp.calorie_calculator.model.entity.Seasoning;

public interface CustomIngredientMapper {

    Seasoning fromDto(CustomIngredientDto customIngredientDto);

    CustomIngredientDto toDto(Seasoning seasoning);

    Seasoning fromDataDto(CustomIngredientDataDto customIngredientDataDto);
}
