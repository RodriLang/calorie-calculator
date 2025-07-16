package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.response.RecipeResponseDto;
import com.trainerapp.calorie_calculator.dto.request.RecipeRequestDto;
import com.trainerapp.calorie_calculator.model.entity.Recipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
                IngredientMapper.class,
                CustomIngredientMapper.class
        })
public interface RecipeMapper {


    Recipe fromDto(RecipeResponseDto recipeResponseDto);

    RecipeResponseDto toDto(Recipe recipe);

    Recipe fromDataDto(RecipeRequestDto recipeRequestDto);
}
