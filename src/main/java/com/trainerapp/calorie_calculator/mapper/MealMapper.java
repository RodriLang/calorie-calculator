package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.response.MealCardResponseDto;
import com.trainerapp.calorie_calculator.dto.response.MealResponseDto;
import com.trainerapp.calorie_calculator.dto.request.MealRequestDto;
import com.trainerapp.calorie_calculator.model.entity.Meal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
                RecipeMapper.class,
                TagMapper.class
        })
public interface MealMapper {

    MealResponseDto toDto(Meal meal);

    MealCardResponseDto toCardDto(Meal meal);

    Meal fromDataDto(MealRequestDto mealRequestDto);
}
