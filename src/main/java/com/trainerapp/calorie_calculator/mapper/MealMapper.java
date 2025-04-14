package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.FoodDto;
import com.trainerapp.calorie_calculator.model.entity.Food;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    // Método para mapear de Food a FoodDto
    FoodDto foodToFoodDto(Food food);

    // Método para mapear de FoodDto a Food
    Food foodDtoToFood(FoodDto foodDto);
}
