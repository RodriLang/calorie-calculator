package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.response.RecipeResponseDto;
import com.trainerapp.calorie_calculator.dto.request.IngredientRequestDto;
import com.trainerapp.calorie_calculator.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes/{recipeId}/ingredients")
@RequiredArgsConstructor
public class RecipeIngredientController {

    private final RecipeService recipeService;

    @PostMapping
    public RecipeResponseDto addIngredient(@PathVariable Long recipeId,
                                           @RequestBody IngredientRequestDto ingredientRequestDto) {
        return recipeService.addIngredientToRecipe(recipeId, ingredientRequestDto);
    }

    @PutMapping("/{ingredientId}")
    public RecipeResponseDto updateIngredient(@PathVariable Long recipeId,
                                              @PathVariable Long ingredientId,
                                              @RequestBody IngredientRequestDto newIngredientData) {
        return recipeService.updateIngredientInRecipe(recipeId, ingredientId, newIngredientData);
    }

    @DeleteMapping("/{ingredientId}")
    public RecipeResponseDto removeIngredient(@PathVariable Long recipeId,
                                              @PathVariable Long ingredientId) {
        return recipeService.removeIngredientFromRecipe(recipeId, ingredientId);
    }
}

