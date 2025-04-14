package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.model.dto.RecipeDto;
import com.trainerapp.calorie_calculator.model.dto.create.IngredientDataDto;
import com.trainerapp.calorie_calculator.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes/{recipeId}/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final RecipeService recipeService;

    @PostMapping
    public RecipeDto addIngredient(@PathVariable Long recipeId,
                                   @RequestBody IngredientDataDto ingredientDataDto) {
        return recipeService.addIngredientToRecipe(recipeId, ingredientDataDto);
    }

    @PutMapping("/{ingredientId}")
    public RecipeDto updateIngredient(@PathVariable Long recipeId,
                                      @PathVariable Long ingredientId,
                                      @RequestBody IngredientDataDto newIngredientData) {
        return recipeService.updateIngredientInRecipe(recipeId, ingredientId, newIngredientData);
    }

    @DeleteMapping("/{ingredientId}")
    public RecipeDto removeIngredient(@PathVariable Long recipeId,
                                      @PathVariable Long ingredientId) {
        return recipeService.removeIngredientFromRecipe(recipeId, ingredientId);
    }
}

