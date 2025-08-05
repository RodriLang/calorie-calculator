package com.trainerapp.calorie_calculator.application.service;

import com.trainerapp.calorie_calculator.domain.model.Ingredient;
import com.trainerapp.calorie_calculator.web.dto.request.IngredientRequestDto;

import java.util.UUID;

public interface IngredientService {

    Ingredient create(IngredientRequestDto ingredientRequestDto);

    Ingredient getModelById(UUID id);

    void update(Ingredient ingredient, IngredientRequestDto ingredientRequestDto);
}
