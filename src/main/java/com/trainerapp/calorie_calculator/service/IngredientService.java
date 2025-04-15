package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.exception.IngredientNotFoundException;
import com.trainerapp.calorie_calculator.mapper.IngredientMapper;
import com.trainerapp.calorie_calculator.model.dto.create.IngredientDataDto;
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
    private final IngredientMapper ingredientMapper;
    private final FoodService foodService;
    private final MeasurementUnitService measurementUnitService;


    public Ingredient create(IngredientDataDto ingredientDataDto) {

        return Ingredient.builder()
                .food(foodService.findEntityById(ingredientDataDto.foodId()))
                .unit(measurementUnitService.findEntityById(ingredientDataDto.measurementUnitId()))
                .quantity(ingredientDataDto.quantity())
                .build();
    }

    public Ingredient getEntityById(Long id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new IngredientNotFoundException(id));
    }

    public void update(Ingredient ingredient, IngredientDataDto data) {
        Food food = foodService.findEntityById(data.foodId());
        MeasurementUnit unit = measurementUnitService.findEntityById(data.measurementUnitId());

        ingredient.setFood(food);
        ingredient.setQuantity(data.quantity());
        ingredient.setUnit(unit);
    }


}

