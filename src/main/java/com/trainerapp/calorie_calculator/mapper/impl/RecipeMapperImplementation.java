package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.mapper.CustomIngredientMapper;
import com.trainerapp.calorie_calculator.mapper.IngredientMapper;
import com.trainerapp.calorie_calculator.mapper.RecipeMapper;
import com.trainerapp.calorie_calculator.dto.RecipeDto;
import com.trainerapp.calorie_calculator.dto.create.RecipeDataDto;
import com.trainerapp.calorie_calculator.model.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class RecipeMapperImplementation implements RecipeMapper {

    private final IngredientMapper ingredientMapper;
    private final CustomIngredientMapper customIngredientMapper;

    @Override
    public Recipe fromDto(RecipeDto recipeDto) {
        if (recipeDto == null) return null;

        return Recipe.builder()
                .id(recipeDto.id())
                .name(recipeDto.name())
                .shortDescription(recipeDto.shortDescription())
                .steps(recipeDto.steps())
                .ingredients(recipeDto.ingredients()
                        .stream()
                        .map(ingredientMapper::fromDto)
                        .toList())
                .customIngredients(Optional.ofNullable(recipeDto.customIngredients())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(customIngredientMapper::fromDto)
                        .toList())
                .difficulty(recipeDto.difficulty())
                .preparationTime(recipeDto.preparationTime())
                .build();
    }

    @Override
    public RecipeDto toDto(Recipe recipe) {
        if (recipe == null) return null;

        return RecipeDto.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .ingredients(recipe.getIngredients()
                        .stream()
                        .map(ingredientMapper::toDto)
                        .toList())
                .shortDescription(recipe.getShortDescription())
                .customIngredients(
                        Optional.ofNullable(recipe.getCustomIngredients())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(customIngredientMapper::toDto)
                                .toList())
                .difficulty(recipe.getDifficulty())
                .preparationTime(recipe.getPreparationTime())
                .steps(recipe.getSteps())
                .build();
    }

    @Override
    public Recipe fromDataDto(RecipeDataDto recipeDataDto) {
        if (recipeDataDto == null) return null;

        return Recipe.builder()
                .name(recipeDataDto.name())
                .shortDescription(recipeDataDto.shortDescription())
                .steps(recipeDataDto.steps())
                .ingredients(recipeDataDto.ingredients()
                        .stream()
                        .map(ingredientMapper::fromDataDto)
                        .toList())
                .customIngredients(Optional.ofNullable(recipeDataDto.customIngredients())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(customIngredientMapper::fromDataDto)
                        .toList())
                .difficulty(recipeDataDto.difficulty())
                .preparationTime(recipeDataDto.preparationTime())
                .build();
    }
}

