package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.response.RecipeResponseDto;
import com.trainerapp.calorie_calculator.dto.request.SeasoningRequestDto;
import com.trainerapp.calorie_calculator.dto.response.SectionResponseDto;
import com.trainerapp.calorie_calculator.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meals/{recipeId}/recipes/{recipeId}/sections/{sectionId}/seasonings")
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

