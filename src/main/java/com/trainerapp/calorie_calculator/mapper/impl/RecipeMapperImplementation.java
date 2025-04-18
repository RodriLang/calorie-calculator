package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.mapper.CustomIngredientMapper;
import com.trainerapp.calorie_calculator.mapper.IngredientMapper;
import com.trainerapp.calorie_calculator.mapper.RecipeMapper;
import com.trainerapp.calorie_calculator.model.dto.RecipeDto;
import com.trainerapp.calorie_calculator.model.dto.create.RecipeDataDto;
import com.trainerapp.calorie_calculator.model.entity.RecipeSection;
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
    public RecipeSection fromDto(RecipeDto recipeDto) {
        if (recipeDto == null) return null;

        return RecipeSection.builder()
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
    public RecipeDto toDto(RecipeSection recipeSection) {
        if (recipeSection == null) return null;

        return RecipeDto.builder()
                .id(recipeSection.getId())
                .name(recipeSection.getName())
                .ingredients(recipeSection.getIngredients()
                        .stream()
                        .map(ingredientMapper::toDto)
                        .toList())
                .shortDescription(recipeSection.getShortDescription())
                .customIngredients(
                        Optional.ofNullable(recipeSection.getCustomIngredients())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(customIngredientMapper::toDto)
                                .toList())
                .difficulty(recipeSection.getDifficulty())
                .preparationTime(recipeSection.getPreparationTime())
                .steps(recipeSection.getSteps())
                .build();
    }

    @Override
    public RecipeSection fromDataDto(RecipeDataDto recipeDataDto) {
        if (recipeDataDto == null) return null;

        return RecipeSection.builder()
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

