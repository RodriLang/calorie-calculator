package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.RecipeDto;
import com.trainerapp.calorie_calculator.model.dto.create.RecipeDataDto;
import com.trainerapp.calorie_calculator.model.entity.Recipe;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface RecipeMapper {

    Recipe fromDto(RecipeDto recipeDto);

    RecipeDto toDto(Recipe recipe);

    Recipe fromDataDto(RecipeDataDto createRecipeDto);
}
