package com.trainerapp.calorie_calculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes/{recipeId}/sections/{sectionId}/seasonings")
@RequiredArgsConstructor
public class SectionSeasoningController {
/*
    private final SectionService sectionService;

    @PostMapping
    public SectionResponseDto addSeasoning(@PathVariable Long recipeId,
                                           @PathVariable Long sectionId,
                                           @RequestBody SeasoningRequestDto dto) {
        return sectionService.addSeasoningToSection(recipeId, sectionId, dto);
    }

    @PutMapping("/{customIngredientId}")
    public RecipeResponseDto updateCustomIngredient(@PathVariable Long recipeId,
                                                    @PathVariable Long customIngredientId,
                                                    @RequestBody SeasoningRequestDto updatedData) {
        return sectionService.updateCustomIngredient(recipeId, customIngredientId, updatedData);
    }

    @DeleteMapping("/{customIngredientId}")
    public RecipeResponseDto removeCustomIngredient(@PathVariable Long recipeId, @PathVariable Long customIngredientId) {
        return sectionService.removeCustomIngredient(recipeId, customIngredientId);
    }

 */
}

