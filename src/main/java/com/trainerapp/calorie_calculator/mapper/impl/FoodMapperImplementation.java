package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.mapper.MicronutrientContentMapper;
import com.trainerapp.calorie_calculator.mapper.NutritionalInfoMapper;
import com.trainerapp.calorie_calculator.mapper.TagMapper;
import com.trainerapp.calorie_calculator.model.dto.FoodDto;
import com.trainerapp.calorie_calculator.model.dto.create.FoodDataDto;
import com.trainerapp.calorie_calculator.model.entity.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FoodMapperImplementation implements com.trainerapp.calorie_calculator.mapper.FoodMapper {

    private final MicronutrientContentMapper micronutrientContentMapper;
    private final NutritionalInfoMapper nutritionalInfoMapper;
    private final TagMapper tagMapper;

    public FoodDto toDto(Food food) {
        return FoodDto.builder()
                .id(food.getId())
                .name(food.getName())
                .foodOrigin(food.getFoodOrigin())
                .nutritionalFunctions(food.getNutritionalFunctions())
                .nutritionalInfo(nutritionalInfoMapper.toDto(food.getNutritionalInfo()))
                .micronutrients(food.getMicronutrients()
                        .stream()
                        .map(micronutrientContentMapper::toDto)
                        .toList())
                .tags(food.getTags()
                        .stream()
                        .map(tagMapper::toDto)
                        .toList())
                .build();
    }

    public Food fromDto(FoodDto foodDto) {
        return null;
    }

    public Food fromDataDto(FoodDataDto foodDataDto) {
        if (foodDataDto == null) return null;

        return Food.builder()
                .name(foodDataDto.name())
                .foodOrigin(foodDataDto.foodOrigin())
                .nutritionalFunctions(foodDataDto.nutritionalFunctions())
                .nutritionalInfo(
                        nutritionalInfoMapper.fromDataDto(foodDataDto.nutritionalInfo())
                )
                .micronutrients(
                        foodDataDto.micronutrients()
                                .stream()
                                .map(micronutrientContentMapper::fromDataDto)
                                .toList()
                )
                .tags(
                        foodDataDto.tags()
                                .stream()
                                .map(tagMapper::fromDataDto) // asumiendo que este método existe
                                .toList()
                )
                .build();
    }

}
