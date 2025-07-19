package com.trainerapp.calorie_calculator.service.impl;

import com.trainerapp.calorie_calculator.exception.IngredientNotFoundException;
import com.trainerapp.calorie_calculator.dto.request.IngredientRequestDto;
import com.trainerapp.calorie_calculator.model.entity.Food;
import com.trainerapp.calorie_calculator.model.entity.Ingredient;
import com.trainerapp.calorie_calculator.model.entity.MeasurementUnit;
import com.trainerapp.calorie_calculator.repository.IngredientRepository;
import com.trainerapp.calorie_calculator.service.IngredientService; // Importar la interfaz
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IngredientServiceImpl implements IngredientService { // Implementar la interfaz

    private final IngredientRepository ingredientRepository;
    private final FoodService foodService;
    private final MeasurementUnitServiceImpl measurementUnitServiceImpl;

    @Override
    public Ingredient create(IngredientRequestDto ingredientRequestDto) {
        Food food = foodService.findEntityById(ingredientRequestDto.foodId());

        return Ingredient.builder()
                .food(food)
                .unit(measurementUnitServiceImpl.findEntityById(ingredientRequestDto.measurementUnitId()))
                .amount(ingredientRequestDto.amount())
                .build();
    }

    @Override
    public Ingredient getEntityById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }

    @Override
    public void update(Ingredient ingredient, IngredientRequestDto ingredientRequestDto) {
        MeasurementUnit unit = measurementUnitServiceImpl.findEntityById(ingredientRequestDto.measurementUnitId());
        Food food = foodService.findEntityById(ingredientRequestDto.foodId());

        ingredient.setFood(food);
        ingredient.setAmount(ingredientRequestDto.amount());
        ingredient.setUnit(unit);
    }
}
