package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.exception.IngredientNotFoundException;
import com.trainerapp.calorie_calculator.mapper.FoodMapper;
import com.trainerapp.calorie_calculator.dto.request.IngredientRequestDto;
import com.trainerapp.calorie_calculator.model.entity.Ingredient;
import com.trainerapp.calorie_calculator.model.entity.MeasurementUnit;
import com.trainerapp.calorie_calculator.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final FoodMapper foodMapper;
    private final FoodService foodService;
    private final MeasurementUnitService measurementUnitService;


    public Ingredient create(IngredientRequestDto ingredientRequestDto) {

        return Ingredient.builder()
                .food(foodMapper.fromDto(ingredientRequestDto.food()))
                .unit(measurementUnitService.findEntityById(ingredientRequestDto.measurementUnitId()))
                .quantity(ingredientRequestDto.quantity())
                .build();
    }

    public Ingredient getEntityById(Long id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new IngredientNotFoundException(id));
    }

    public void update(Ingredient ingredient, IngredientRequestDto data) {
        MeasurementUnit unit = measurementUnitService.findEntityById(data.measurementUnitId());

        ingredient.setFood(foodMapper.fromDto(data.food()));
        ingredient.setQuantity(data.quantity());
        ingredient.setUnit(unit);
    }


}

