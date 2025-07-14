package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.FoodDto;
import com.trainerapp.calorie_calculator.dto.create.FoodDataDto;
import com.trainerapp.calorie_calculator.model.entity.Food;

public interface FoodMapper {

    FoodDto toDto(Food food);

    Food fromDto(FoodDto foodDto);

    Food fromDataDto(FoodDataDto foodDataDto);



}
