package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.dto.request.IngredientRequestDto;
import com.trainerapp.calorie_calculator.model.entity.Ingredient;

public interface IngredientService {
    Ingredient create(IngredientRequestDto ingredientRequestDto);
    Ingredient getEntityById(Long id);
    void update(Ingredient ingredient, IngredientRequestDto ingredientRequestDto);
}
