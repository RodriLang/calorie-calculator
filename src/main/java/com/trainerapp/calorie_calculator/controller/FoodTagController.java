package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.response.FoodResponseDto;
import com.trainerapp.calorie_calculator.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.service.impl.FoodService;
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
    public ResponseEntity<FoodResponseDto> addTags(
            @PathVariable Long foodId,
            @RequestBody List<TagRequestDto> tags) {
        return ResponseEntity.ok(foodService.addTags(foodId, tags));
    }

    @DeleteMapping("/{foodId}/tags")
    public ResponseEntity<FoodResponseDto> removeTags(
            @PathVariable Long foodId,
            @RequestBody List<Long> tagIds) {
        return ResponseEntity.ok(foodService.removeTags(foodId, tagIds));
    }
}
