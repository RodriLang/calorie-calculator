package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.mapper.RecipeMapper;
import com.trainerapp.calorie_calculator.mapper.RecipeSectionMapper;
import com.trainerapp.calorie_calculator.mapper.TagMapper;
import com.trainerapp.calorie_calculator.model.dto.RecipeCardDto;
import com.trainerapp.calorie_calculator.model.dto.RecipeDto;
import com.trainerapp.calorie_calculator.model.dto.create.RecipeDataDto;
import com.trainerapp.calorie_calculator.model.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RecipeMapperImplementation implements RecipeMapper {

    private final RecipeSectionMapper recipeSectionMapper;
    private final TagMapper tagMapper;

    public RecipeDto toDto(Recipe recipe) {
        return RecipeDto.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .description(recipe.getDescription())
                .url(recipe.getUrl())
                .recipeList(recipe.getRecipeSectionList()
                        .stream()
                        .map(recipeSectionMapper::toDto)
                        .toList())
                .tagList(Optional.ofNullable(recipe.getTagList())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(tagMapper::toDto)
                                .toList())
                .flavorType(recipe.getFlavorType())
                .build();
    }

    public RecipeCardDto toCardDto(Recipe recipe) {
        if (recipe == null) return null;


        return RecipeCardDto.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .url(recipe.getUrl())
                .difficulty(recipe.getDifficulty().name())
                .preparationTime(recipe.getPreparationTime())
                .tags(recipe.getTagList()
                        .stream()
                        .map(tagMapper::toDto)
                        .toList())
                .build();
    }


    public Recipe fromDataDto(RecipeDataDto recipeDataDto) {
        return null;
    }
}
