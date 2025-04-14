package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.FoodDto;
import com.trainerapp.calorie_calculator.model.dto.create.FoodDataDto;
import com.trainerapp.calorie_calculator.model.entity.Food;
import com.trainerapp.calorie_calculator.service.FoodService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        FoodService.class,
        NutritionalInfoMapper.class,
        MicronutrientContentMapper.class,
        TagMapper.class,
        RecipeMapper.class
})
public interface FoodMapper {

    FoodDto toDto(Food food);

    Food fromDto(FoodDto foodDto);

    Food fromDataDto(FoodDataDto foodDataDto);



}
