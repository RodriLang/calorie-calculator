package com.trainerapp.calorie_calculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes/{recipeId}/steps")
@RequiredArgsConstructor
public class RecipeStepController {
/*
    private final SectionService sectionService;

    @PostMapping
    public RecipeResponseDto addStep(@PathVariable Long recipeId,
                                     @RequestBody String stepDescription) {
        return sectionService.addStepToRecipe(recipeId, stepDescription);
    }

    @PutMapping("/{stepIndex}")
    public RecipeResponseDto updateStep(@PathVariable Long recipeId,
                                        @PathVariable int stepIndex,
                                        @RequestBody String newStepDescription) {
        return sectionService.updateStepInRecipe(recipeId, stepIndex, newStepDescription);
    }

    @DeleteMapping
    public RecipeResponseDto removeStep(@PathVariable Long recipeId,
                                        @RequestBody String stepDescription) {
        return sectionService.removeStepFromRecipe(recipeId, stepDescription);
    }

 */
}