package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.MealCardDto;
import com.trainerapp.calorie_calculator.model.dto.MealDto;
import com.trainerapp.calorie_calculator.model.dto.create.MealDataDto;
import com.trainerapp.calorie_calculator.model.entity.Meal;

public interface MealMapper {

    MealDto toDto(Meal meal);

    MealCardDto toCardDto(Meal meal);

    Meal fromDataDto(MealDataDto mealDataDto);
}
