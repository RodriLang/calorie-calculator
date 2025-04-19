package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.model.dto.RecipeSectionDto;
import com.trainerapp.calorie_calculator.service.RecipeSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes/{recipeId}/steps")
@RequiredArgsConstructor
public class RecipeStepController {

    private final RecipeSectionService recipeSectionService;

    @PostMapping
    public RecipeSectionDto addStep(@PathVariable Long recipeId,
                                    @RequestBody String stepDescription) {
        return recipeSectionService.addStepToRecipe(recipeId, stepDescription);
    }

    @PutMapping("/{stepIndex}")
    public RecipeSectionDto updateStep(@PathVariable Long recipeId,
                                       @PathVariable int stepIndex,
                                       @RequestBody String newStepDescription) {
        return recipeSectionService.updateStepInRecipe(recipeId, stepIndex, newStepDescription);
    }

    @DeleteMapping
    public RecipeSectionDto removeStep(@PathVariable Long recipeId,
                                       @RequestBody String stepDescription) {
        return recipeSectionService.removeStepFromRecipe(recipeId, stepDescription);
    }
}