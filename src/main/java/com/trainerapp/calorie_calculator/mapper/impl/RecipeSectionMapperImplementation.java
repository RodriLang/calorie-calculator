package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.mapper.SeasoningMapper;
import com.trainerapp.calorie_calculator.mapper.IngredientMapper;
import com.trainerapp.calorie_calculator.mapper.RecipeSectionMapper;
import com.trainerapp.calorie_calculator.model.dto.RecipeSectionDto;
import com.trainerapp.calorie_calculator.model.dto.create.RecipeSectionDataDto;
import com.trainerapp.calorie_calculator.model.entity.RecipeSection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class RecipeSectionMapperImplementation implements RecipeSectionMapper {

    private final IngredientMapper ingredientMapper;
    private final SeasoningMapper seasoningMapper;

    @Override
    public RecipeSection fromDto(RecipeSectionDto recipeSectionDto) {
        if (recipeSectionDto == null) return null;

        return RecipeSection.builder()
                .id(recipeSectionDto.id())
                .title(recipeSectionDto.name())

                .steps(recipeSectionDto.steps())
                .ingredients(recipeSectionDto.ingredients()
                        .stream()
                        .map(ingredientMapper::fromDto)
                        .toList())
                .seasonings(Optional.ofNullable(recipeSectionDto.seasonings())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(seasoningMapper::fromDto)
                        .toList())
                .build();
    }

    @Override
    public RecipeSectionDto toDto(RecipeSection recipeSection) {
        if (recipeSection == null) return null;

        return RecipeSectionDto.builder()
                .id(recipeSection.getId())
                .name(recipeSection.getTitle())
                .ingredients(recipeSection.getIngredients()
                        .stream()
                        .map(ingredientMapper::toDto)
                        .toList())

                .seasonings(
                        Optional.ofNullable(recipeSection.getSeasonings())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(seasoningMapper::toDto)
                                .toList())

                .steps(recipeSection.getSteps())
                .build();
    }

    @Override
    public RecipeSection fromDataDto(RecipeSectionDataDto recipeSectionDataDto) {
        if (recipeSectionDataDto == null) return null;

        return RecipeSection.builder()
                .title(recipeSectionDataDto.name())

                .steps(recipeSectionDataDto.steps())
                .ingredients(recipeSectionDataDto.ingredients()
                        .stream()
                        .map(ingredientMapper::fromDataDto)
                        .toList())
                .seasonings(Optional.ofNullable(recipeSectionDataDto.customIngredients())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(seasoningMapper::fromDataDto)
                        .toList())
                .build();
    }
}

