package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.MealDto;
import com.trainerapp.calorie_calculator.dto.create.TagDataDto;
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
    public ResponseEntity<MealDto> addTags(@PathVariable Long mealId, @RequestBody List<TagDataDto> tags) {
        return ResponseEntity.ok(mealService.addTags(mealId, tags));
    }

    @DeleteMapping("/{mealId}/tags")
    public ResponseEntity<MealDto> removeTags(@PathVariable Long mealId, @RequestBody List<Long> tagIds) {
        return ResponseEntity.ok(mealService.removeTags(mealId, tagIds));
    }
}

