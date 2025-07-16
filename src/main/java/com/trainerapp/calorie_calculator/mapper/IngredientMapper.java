package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.response.IngredientResponseDto;
import com.trainerapp.calorie_calculator.dto.request.IngredientRequestDto;
import com.trainerapp.calorie_calculator.model.entity.Ingredient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = FoodMapper.class)
public interface IngredientMapper {

    Ingredient fromDto(IngredientResponseDto ingredientResponseDto);

    IngredientResponseDto toDto(Ingredient ingredient);

    Ingredient fromDataDto(IngredientRequestDto ingredientRequestDto);

}

