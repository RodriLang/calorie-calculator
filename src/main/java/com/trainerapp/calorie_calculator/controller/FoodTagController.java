package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.FoodDto;
import com.trainerapp.calorie_calculator.dto.create.TagDataDto;
import com.trainerapp.calorie_calculator.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calorie-calculator/foods")
public class FoodTagController {

    private final FoodService foodService;

    @PutMapping("/{foodId}/tags")
    public ResponseEntity<FoodDto> addTags(
            @PathVariable Long foodId,
            @RequestBody List<TagDataDto> tags) {
        return ResponseEntity.ok(foodService.addTags(foodId, tags));
    }

    @DeleteMapping("/{foodId}/tags")
    public ResponseEntity<FoodDto> removeTags(
            @PathVariable Long foodId,
            @RequestBody List<Long> tagIds) {
        return ResponseEntity.ok(foodService.removeTags(foodId, tagIds));
    }
}
