package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.MealDto;
import com.trainerapp.calorie_calculator.model.dto.create.MealDataDto;
import com.trainerapp.calorie_calculator.model.entity.Meal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        IngredientMapper.class,
        NutrientValueMapper.class
})
public interface MealMapper {

    MealDto toDto(Meal meal);

    Meal fromDto(MealDto mealDto);

    Meal fromDataDto(MealDataDto mealDataDto);
}
