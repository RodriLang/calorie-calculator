package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.IngredientDto;
import com.trainerapp.calorie_calculator.model.dto.NutrientValueDto;
import com.trainerapp.calorie_calculator.model.dto.create.IngredientDataDto;
import com.trainerapp.calorie_calculator.model.entity.Ingredient;


public interface IngredientMapper {

    Ingredient fromDto(IngredientDto ingredientDto);

    IngredientDto toDto(Ingredient ingredient);

    Ingredient fromDataDto(IngredientDataDto ingredientDataDto);

}

