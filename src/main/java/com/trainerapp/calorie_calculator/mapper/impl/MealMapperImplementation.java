package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.enums.DifficultyType;
import com.trainerapp.calorie_calculator.mapper.MealMapper;
import com.trainerapp.calorie_calculator.mapper.RecipeMapper;
import com.trainerapp.calorie_calculator.mapper.TagMapper;
import com.trainerapp.calorie_calculator.model.dto.MealCardDto;
import com.trainerapp.calorie_calculator.model.dto.MealDto;
import com.trainerapp.calorie_calculator.model.dto.create.MealDataDto;
import com.trainerapp.calorie_calculator.model.entity.Recipe;
import com.trainerapp.calorie_calculator.model.entity.RecipeSection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MealMapperImplementation implements MealMapper {

    private final RecipeMapper recipeMapper;
    private final TagMapper tagMapper;

    public MealDto toDto(Recipe recipe) {
        return MealDto.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .shortDescription(recipe.getDescription())
                .url(recipe.getUrl())
                .recipeList(recipe.getRecipeSectionList()
                        .stream()
                        .map(recipeMapper::toDto)
                        .toList())
                .tagList(Optional.ofNullable(recipe.getTags())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(tagMapper::toDto)
                                .toList())
                .build();
    }

    public MealCardDto toCardDto(Recipe recipe) {
        if (recipe == null) return null;

        // Obtener la mayor dificultad
        DifficultyType maxDifficulty = recipe.getRecipeSectionList()
                .stream()
                .map(RecipeSection::getDifficulty)
                .max(Comparator.naturalOrder())  // si Difficulty es Enum o Comparable
                .orElse(null);

        // Sumar los tiempos de preparación
        Duration totalPreparationTime = recipe.getRecipeSectionList()
                .stream()
                .map(RecipeSection::getPreparationTime)
                .reduce(Duration.ZERO, Duration::plus);

        String formattedTime;
        long hours = totalPreparationTime.toHours();
        int minutes = totalPreparationTime.toMinutesPart();

        if (hours > 0 && minutes > 0) {
            formattedTime = String.format("%d %s, %d %s",
                    hours, DurationType.HOUR.getAbbreviationBasedOnQuantity(hours),
                    minutes, DurationType.MINUTE.getAbbreviationBasedOnQuantity(minutes));
        } else if (hours > 0) {
            formattedTime = String.format("%d %s",
                    hours, DurationType.HOUR.getAbbreviationBasedOnQuantity(hours));
        } else {
            formattedTime = String.format("%d %s",
                    minutes, DurationType.MINUTE.getAbbreviationBasedOnQuantity(minutes));
        }


        return MealCardDto.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .url(recipe.getUrl())
                .difficulty(String.valueOf(maxDifficulty))
                .preparationTime(formattedTime)
                .tags(recipe.getTags()
                        .stream()
                        .map(tagMapper::toDto)
                        .toList())
                .build();
    }


    public Recipe fromDataDto(MealDataDto mealDataDto) {
        return null;
    }
}
