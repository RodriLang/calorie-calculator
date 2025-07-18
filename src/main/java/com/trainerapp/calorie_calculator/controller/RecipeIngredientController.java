package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.response.RecipeResponseDto;
import com.trainerapp.calorie_calculator.dto.request.IngredientRequestDto;
import com.trainerapp.calorie_calculator.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes/{recipeId}/ingredients")
@RequiredArgsConstructor
public class RecipeIngredientController {
/*
    private final SectionService sectionService;

    @PostMapping
    public RecipeResponseDto addIngredient(@PathVariable Long recipeId,
                                           @RequestBody IngredientRequestDto ingredientRequestDto) {
        return sectionService.addIngredientToRecipe(recipeId, ingredientRequestDto);
    }

    @PutMapping("/{ingredientId}")
    public RecipeResponseDto updateIngredient(@PathVariable Long recipeId,
                                              @PathVariable Long ingredientId,
                                              @RequestBody IngredientRequestDto newIngredientData) {
        return sectionService.updateIngredientInRecipe(recipeId, ingredientId, newIngredientData);
    }

    @DeleteMapping("/{ingredientId}")
    public RecipeResponseDto removeIngredient(@PathVariable Long recipeId,
                                              @PathVariable Long ingredientId) {
        return sectionService.removeIngredientFromRecipe(recipeId, ingredientId);
    }

 */
}

