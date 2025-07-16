package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.response.FoodResponseDto;
import com.trainerapp.calorie_calculator.dto.request.FoodRequestDto;
import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.dto.update.FoodUpdateDto;
import com.trainerapp.calorie_calculator.service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calorie-calculator/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    public ResponseEntity<FoodResponseDto> save(@RequestBody FoodRequestDto food) {
        return ResponseEntity.ok(foodService.save(food));
    }

    @GetMapping
    public ResponseEntity<List<FoodResponseDto>> getAll() {
        return ResponseEntity.ok(foodService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(foodService.getById(id));
    }

    @GetMapping("/origin")
    public ResponseEntity<List<FoodResponseDto>> getByFoodOrigin(@RequestParam FoodOriginType origin) {
        return ResponseEntity.ok(foodService.findByFoodOrigin(origin));
    }

    @GetMapping("/calories")
    public ResponseEntity<List<FoodResponseDto>> getByCaloriesBetween(
            @RequestParam int min,
            @RequestParam int max) {
        return ResponseEntity.ok(foodService.findByCaloriesBetween(min, max));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodResponseDto> update(
            @PathVariable Long id,
            @RequestBody FoodRequestDto foodRequestDto) {
        return ResponseEntity.ok(foodService.update(id, foodRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FoodResponseDto> partialUpdate(
            @PathVariable Long id,
            @RequestBody FoodUpdateDto foodUpdateDto) {
        return ResponseEntity.ok(foodService.parcialUpdate(id, foodUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        foodService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}