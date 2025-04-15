package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.enums.DifficultyType;
import com.trainerapp.calorie_calculator.enums.DurationType;
import com.trainerapp.calorie_calculator.mapper.MealMapper;
import com.trainerapp.calorie_calculator.mapper.RecipeMapper;
import com.trainerapp.calorie_calculator.mapper.TagMapper;
import com.trainerapp.calorie_calculator.model.dto.MealCardDto;
import com.trainerapp.calorie_calculator.model.dto.MealDto;
import com.trainerapp.calorie_calculator.model.dto.create.MealDataDto;
import com.trainerapp.calorie_calculator.model.entity.Meal;
import com.trainerapp.calorie_calculator.model.entity.Recipe;
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

    public MealDto toDto(Meal meal) {
        return MealDto.builder()
                .id(meal.getId())
                .name(meal.getName())
                .shortDescription(meal.getShortDescription())
                .url(meal.getUrl())
                .recipeList(meal.getRecipeList()
                        .stream()
                        .map(recipeMapper::toDto)
                        .toList())
                .tagList(Optional.ofNullable(meal.getTagList())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(tagMapper::toDto)
                                .toList())
                .build();
    }

    public MealCardDto toCardDto(Meal meal) {
        if (meal == null) return null;

        // Obtener la mayor dificultad
        DifficultyType maxDifficulty = meal.getRecipeList()
                .stream()
                .map(Recipe::getDifficulty)
                .max(Comparator.naturalOrder())  // si Difficulty es Enum o Comparable
                .orElse(null);

        // Sumar los tiempos de preparación
        Duration totalPreparationTime = meal.getRecipeList()
                .stream()
                .map(Recipe::getPreparationTime)
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
                .id(meal.getId())
                .name(meal.getName())
                .url(meal.getUrl())
                .difficulty(String.valueOf(maxDifficulty))
                .preparationTime(formattedTime)
                .tags(meal.getTagList()
                        .stream()
                        .map(tagMapper::toDto)
                        .toList())
                .build();
    }


    public Meal fromDataDto(MealDataDto mealDataDto) {
        return null;
    }
}
