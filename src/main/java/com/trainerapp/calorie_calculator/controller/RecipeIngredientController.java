package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.model.dto.RecipeSectionDto;
import com.trainerapp.calorie_calculator.model.dto.create.IngredientDataDto;
import com.trainerapp.calorie_calculator.service.RecipeSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes/{recipeId}/ingredients")
@RequiredArgsConstructor
public class RecipeIngredientController {

    private final RecipeSectionService recipeSectionService;

    @PostMapping
    public RecipeSectionDto addIngredient(@PathVariable Long recipeId,
                                          @RequestBody IngredientDataDto ingredientDataDto) {
        return recipeSectionService.addIngredientToRecipe(recipeId, ingredientDataDto);
    }

    @PutMapping("/{ingredientId}")
    public RecipeSectionDto updateIngredient(@PathVariable Long recipeId,
                                             @PathVariable Long ingredientId,
                                             @RequestBody IngredientDataDto newIngredientData) {
        return recipeSectionService.updateIngredientInRecipe(recipeId, ingredientId, newIngredientData);
    }

    @DeleteMapping("/{ingredientId}")
    public RecipeSectionDto removeIngredient(@PathVariable Long recipeId,
                                             @PathVariable Long ingredientId) {
        return recipeSectionService.removeIngredientFromRecipe(recipeId, ingredientId);
    }
}

