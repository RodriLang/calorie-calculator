package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.RecipeDto;
import com.trainerapp.calorie_calculator.model.dto.create.RecipeDataDto;
import com.trainerapp.calorie_calculator.model.entity.RecipeSection;


public interface RecipeMapper {


    RecipeSection fromDto(RecipeDto recipeDto);

    RecipeDto toDto(RecipeSection recipeSection);

    RecipeSection fromDataDto(RecipeDataDto recipeDataDto);
}
