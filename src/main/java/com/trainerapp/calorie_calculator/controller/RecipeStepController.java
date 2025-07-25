package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.response.RecipeResponseDto;
import com.trainerapp.calorie_calculator.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes/{recipeId}/steps")
@RequiredArgsConstructor
public class RecipeStepController {

    private final RecipeService recipeService;

    @PostMapping
    public RecipeResponseDto addStep(@PathVariable Long recipeId,
                                     @RequestBody String stepDescription) {
        return recipeService.addStepToRecipe(recipeId, stepDescription);
    }

    @PutMapping("/{stepIndex}")
    public RecipeResponseDto updateStep(@PathVariable Long recipeId,
                                        @PathVariable int stepIndex,
                                        @RequestBody String newStepDescription) {
        return recipeService.updateStepInRecipe(recipeId, stepIndex, newStepDescription);
    }

    @DeleteMapping
    public RecipeResponseDto removeStep(@PathVariable Long recipeId,
                                        @RequestBody String stepDescription) {
        return recipeService.removeStepFromRecipe(recipeId, stepDescription);
    }
}