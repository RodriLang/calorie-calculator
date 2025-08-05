package com.trainerapp.calorie_calculator.web.controller;

import com.trainerapp.calorie_calculator.web.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.RecipeResponseDto;
import com.trainerapp.calorie_calculator.application.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calorie-calculator/recipes")

public class RecipeTagController {

    private final RecipeService recipeService;

    @PutMapping("/{recipeId}/tags")
    public ResponseEntity<RecipeResponseDto> addTags(
            @PathVariable UUID recipeId,
            @RequestBody List<TagRequestDto> tags) {
        return ResponseEntity.ok(recipeService.addTags(recipeId, tags));
    }

    @DeleteMapping("/{recipeId}/tags")
    public ResponseEntity<RecipeResponseDto> removeTags(
            @PathVariable UUID recipeId,
            @RequestBody List<UUID> tagIds) {
        return ResponseEntity.ok(recipeService.removeTags(recipeId, tagIds));
    }


}

