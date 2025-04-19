package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.RecipeCardDto;
import com.trainerapp.calorie_calculator.model.dto.RecipeDto;
import com.trainerapp.calorie_calculator.model.dto.create.RecipeDataDto;
import com.trainerapp.calorie_calculator.model.entity.Recipe;

public interface RecipeMapper {

    RecipeDto toDto(Recipe recipe);

    RecipeCardDto toCardDto(Recipe recipe);

    Recipe fromDataDto(RecipeDataDto recipeDataDto);
}
