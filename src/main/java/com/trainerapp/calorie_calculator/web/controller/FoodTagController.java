package com.trainerapp.calorie_calculator.web.controller;

import com.trainerapp.calorie_calculator.web.dto.response.FoodResponseDto;
import com.trainerapp.calorie_calculator.web.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.application.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calorie-calculator/foods")
public class FoodTagController {

    private final FoodService foodService;

    @PutMapping("/{foodId}/tags")
    public ResponseEntity<FoodResponseDto> addTags(
            @PathVariable UUID foodId,
            @RequestBody List<TagRequestDto> tags) {
        return ResponseEntity.ok(foodService.addTags(foodId, tags));
    }

    @DeleteMapping("/{foodId}/tags")
    public ResponseEntity<FoodResponseDto> removeTags(
            @PathVariable UUID foodId,
            @RequestBody List<UUID> tagIds) {
        return ResponseEntity.ok(foodService.removeTags(foodId, tagIds));
    }
}
