package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.RecipeDto;
import com.trainerapp.calorie_calculator.dto.create.CustomIngredientDataDto;
import com.trainerapp.calorie_calculator.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes/{recipeId}/custom-ingredients")
@RequiredArgsConstructor
public class RecipeCustomIngredientController {

    private final RecipeService recipeService;

    @PostMapping
    public RecipeDto addCustomIngredient(@PathVariable Long recipeId,
                                         @RequestBody CustomIngredientDataDto dto) {
        return recipeService.addCustomIngredient(recipeId, dto);
    }

    @PutMapping("/{customIngredientId}")
    public RecipeDto updateCustomIngredient(@PathVariable Long recipeId,
                                            @PathVariable Long customIngredientId,
                                            @RequestBody CustomIngredientDataDto updatedData) {
        return recipeService.updateCustomIngredient(recipeId, customIngredientId, updatedData);
    }

    @DeleteMapping("/{customIngredientId}")
    public RecipeDto removeCustomIngredient(@PathVariable Long recipeId, @PathVariable Long customIngredientId) {
        return recipeService.removeCustomIngredient(recipeId, customIngredientId);
    }
}

