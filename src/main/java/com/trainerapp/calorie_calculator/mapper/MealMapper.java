package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.MealCardDto;
import com.trainerapp.calorie_calculator.model.dto.MealDto;
import com.trainerapp.calorie_calculator.model.dto.create.MealDataDto;
import com.trainerapp.calorie_calculator.model.entity.Recipe;

public interface MealMapper {

    MealDto toDto(Recipe recipe);

    MealCardDto toCardDto(Recipe recipe);

    Recipe fromDataDto(MealDataDto mealDataDto);
}
