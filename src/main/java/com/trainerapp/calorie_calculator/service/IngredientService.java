package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.exception.IngredientNotFoundException;
import com.trainerapp.calorie_calculator.mapper.FoodMapper;
import com.trainerapp.calorie_calculator.dto.request.IngredientRequestDto;
import com.trainerapp.calorie_calculator.model.entity.Food;
import com.trainerapp.calorie_calculator.model.entity.Ingredient;
import com.trainerapp.calorie_calculator.model.entity.MeasurementUnit;
import com.trainerapp.calorie_calculator.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final FoodService foodService;
    private final MeasurementUnitService measurementUnitService;


    public Ingredient create(IngredientRequestDto ingredientRequestDto) {

        Food food = foodService.findEntityById(ingredientRequestDto.foodId());

        return Ingredient.builder()
                .food(food)
                .unit(measurementUnitService.findEntityById(ingredientRequestDto.measurementUnitId()))
                .amount(ingredientRequestDto.amount())
                .build();
    }

    public Ingredient getEntityById(Long id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new IngredientNotFoundException(id));
    }

    public void update(Ingredient ingredient, IngredientRequestDto ingredientRequestDto) {
        MeasurementUnit unit = measurementUnitService.findEntityById(ingredientRequestDto.measurementUnitId());
        Food food = foodService.findEntityById(ingredientRequestDto.foodId());

        ingredient.setFood(food);
        ingredient.setAmount(ingredientRequestDto.amount());
        ingredient.setUnit(unit);
    }


}

