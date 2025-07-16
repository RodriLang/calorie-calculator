package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.response.MealResponseDto;
import com.trainerapp.calorie_calculator.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calorie-calculator/meals")

public class MealTagController {

    private final MealService mealService;

    @PutMapping("/{mealId}/tags")
    public ResponseEntity<MealResponseDto> addTags(@PathVariable Long mealId, @RequestBody List<TagRequestDto> tags) {
        return ResponseEntity.ok(mealService.addTags(mealId, tags));
    }

    @DeleteMapping("/{mealId}/tags")
    public ResponseEntity<MealResponseDto> removeTags(@PathVariable Long mealId, @RequestBody List<Long> tagIds) {
        return ResponseEntity.ok(mealService.removeTags(mealId, tagIds));
    }
}

