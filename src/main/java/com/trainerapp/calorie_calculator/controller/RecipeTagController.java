package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.model.dto.RecipeDto;
import com.trainerapp.calorie_calculator.model.dto.create.TagDataDto;
import com.trainerapp.calorie_calculator.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calorie-calculator/recipes")

public class RecipeTagController {

    private final RecipeService recipeService;

    @PutMapping("/{recipeId}/tags")
    public ResponseEntity<RecipeDto> addTags(@PathVariable Long recipeId, @RequestBody List<TagDataDto> tags) {
        return ResponseEntity.ok(recipeService.addTags(recipeId, tags));
    }

    @DeleteMapping("/{recipeId}/tags")
    public ResponseEntity<RecipeDto> removeTags(@PathVariable Long recipeId, @RequestBody List<Long> tagIds) {
        return ResponseEntity.ok(recipeService.removeTags(recipeId, tagIds));
    }
}

