package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.mapper.FoodMapper;
import com.trainerapp.calorie_calculator.mapper.IngredientMapper;
import com.trainerapp.calorie_calculator.mapper.MeasurementUnitMapper;
import com.trainerapp.calorie_calculator.dto.IngredientDto;
import com.trainerapp.calorie_calculator.dto.create.IngredientDataDto;
import com.trainerapp.calorie_calculator.model.entity.Ingredient;
import com.trainerapp.calorie_calculator.model.entity.MeasurementUnit;
import com.trainerapp.calorie_calculator.service.FoodService;
import com.trainerapp.calorie_calculator.service.MeasurementUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredientMapperImplementation implements IngredientMapper {

    private final FoodMapper foodMapper;
    private final FoodService foodService;
    private final MeasurementUnitService measurementUnitService;
    private final MeasurementUnitMapper measurementUnitMapper;

    public Ingredient fromDto(IngredientDto ingredientDto) {
        if (ingredientDto == null) return Ingredient.builder().build();


        return Ingredient.builder()
                .food(foodMapper.fromDto(ingredientDto.food()))
                .quantity(ingredientDto.quantity())
                .unit(measurementUnitMapper.fromDto(ingredientDto.unit()))
                .build();
    }

    public IngredientDto toDto(Ingredient ingredient) {
        if (ingredient == null) return null;

        return IngredientDto.builder()
                .food(foodMapper.toDto(ingredient.getFood()))
                .quantity(ingredient.getQuantity())
                .unit(measurementUnitMapper.toDto(ingredient.getUnit()))
                .build();
    }

    public Ingredient fromDataDto(IngredientDataDto ingredientDataDto) {
        if (ingredientDataDto == null) return null;
        MeasurementUnit measurementUnit = measurementUnitService.findEntityById(ingredientDataDto.measurementUnitId());
        return Ingredient.builder()
                .food(foodMapper.fromDto(ingredientDataDto.food()))
                .quantity(ingredientDataDto.quantity())
                .unit(measurementUnit)
                .build();
    }

}
