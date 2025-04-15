package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.enums.DifficultyType;
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
import java.util.Comparator;

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
                .tagList(meal.getTagList()
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

        return MealCardDto.builder()
                .id(meal.getId())
                .name(meal.getName())
                .url(meal.getUrl())
                .difficulty(String.valueOf(maxDifficulty))
                .preparationTime(totalPreparationTime.toString()) // o formateado si querés
                .build();
    }


    public Meal fromDataDto(MealDataDto mealDataDto) {
        return null;
    }
}
