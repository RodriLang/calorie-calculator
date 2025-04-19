package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.model.dto.RecipeSectionDto;
import com.trainerapp.calorie_calculator.model.dto.create.SeasoningDataDto;
import com.trainerapp.calorie_calculator.service.RecipeSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes/{recipeId}/custom-ingredients")
@RequiredArgsConstructor
public class SeasoningController {

    private final RecipeSectionService recipeSectionService;

    @PostMapping
    public RecipeSectionDto addSeasoning(@PathVariable Long recipeId,
                                                @RequestBody SeasoningDataDto dto) {
        return recipeSectionService.addSeasoning(recipeId, dto);
    }

    @PutMapping("/{customIngredientId}")
    public RecipeSectionDto updateSeasoning(@PathVariable Long recipeId,
                                                   @PathVariable Long customIngredientId,
                                                   @RequestBody SeasoningDataDto updatedData) {
        return recipeSectionService.updateSeasoning(recipeId, customIngredientId, updatedData);
    }

    @DeleteMapping("/{customIngredientId}")
    public RecipeSectionDto removeSeasoning(@PathVariable Long recipeId, @PathVariable Long customIngredientId) {
        return recipeSectionService.removeSeasoning(recipeId, customIngredientId);
    }
}

