package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.FoodDto;
import com.trainerapp.calorie_calculator.model.dto.create.FoodDataDto;
import com.trainerapp.calorie_calculator.model.entity.Food;

public interface FoodMapper {

    FoodDto toDto(Food food);

    Food fromDto(FoodDto foodDto);

    Food fromDataDto(FoodDataDto foodDataDto);



}
