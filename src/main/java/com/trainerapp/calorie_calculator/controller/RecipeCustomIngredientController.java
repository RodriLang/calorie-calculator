package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.response.RecipeResponseDto;
import com.trainerapp.calorie_calculator.dto.request.CustomIngredientRequestDto;
import com.trainerapp.calorie_calculator.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes/{recipeId}/custom-ingredients")
@RequiredArgsConstructor
public class RecipeCustomIngredientController {

    private final RecipeService recipeService;

    @PostMapping
    public RecipeResponseDto addCustomIngredient(@PathVariable Long recipeId,
                                                 @RequestBody CustomIngredientRequestDto dto) {
        return recipeService.addCustomIngredient(recipeId, dto);
    }

    @PutMapping("/{customIngredientId}")
    public RecipeResponseDto updateCustomIngredient(@PathVariable Long recipeId,
                                                    @PathVariable Long customIngredientId,
                                                    @RequestBody CustomIngredientRequestDto updatedData) {
        return recipeService.updateCustomIngredient(recipeId, customIngredientId, updatedData);
    }

    @DeleteMapping("/{customIngredientId}")
    public RecipeResponseDto removeCustomIngredient(@PathVariable Long recipeId, @PathVariable Long customIngredientId) {
        return recipeService.removeCustomIngredient(recipeId, customIngredientId);
    }
}

