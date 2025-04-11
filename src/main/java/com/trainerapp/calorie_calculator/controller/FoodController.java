package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.model.dto.FoodDto;
import com.trainerapp.calorie_calculator.model.entity.Food;
import com.trainerapp.calorie_calculator.model.entity.MeasurementUnit;
import com.trainerapp.calorie_calculator.model.entity.MicronutrientContent;
import com.trainerapp.calorie_calculator.service.FoodService;
import com.trainerapp.calorie_calculator.utils.mapper.FoodMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/save")
    public ResponseEntity<Food> save(@RequestBody Food food) {
        return ResponseEntity.ok(foodService.save(food));
    }

    @GetMapping()
    public ResponseEntity<List<FoodDto>> getAll() {

        return ResponseEntity.ok(foodService.getAll()
                .stream()
                .map(FoodMapper::mapToDto)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodDto> getById(@PathVariable int id) {
        return ResponseEntity.ok(FoodMapper.mapToDto(foodService.getById(id)));
    }

    @GetMapping("/origin")
    public ResponseEntity<List<FoodDto>> getByFoodOrigin(@RequestParam FoodOriginType origin) {
        return ResponseEntity.ok(foodService.findByFoodOrigin(origin)
                .stream()
                .map(FoodMapper::mapToDto)
                .toList());
    }


    @GetMapping("/calories")
    public ResponseEntity<List<FoodDto>> getByCalories(@RequestParam int calories) {

        return ResponseEntity.ok(foodService.findByCalories(calories)
                .stream()
                .map(FoodMapper::mapToDto)
                .toList());
    }

    @GetMapping("/calories-between")
    public ResponseEntity<List<FoodDto>> getByCaloriesBetween(
            @RequestParam int minCalories,
            @RequestParam int maxCalories) {
        return ResponseEntity.ok(foodService.findByCaloriesBetween(minCalories, maxCalories)
                .stream()
                .map(FoodMapper::mapToDto)
                .toList());
    }

    @PatchMapping("/add-micronutrient")
    public ResponseEntity<String> addMicronutrient(@RequestBody MicronutrientContent micronutrient) {

        return ResponseEntity.ok("");
    }

    @PatchMapping("/add-micronutrients")
    public ResponseEntity<String> addMicronutrients(@RequestBody List<MicronutrientContent> micronutrients) {

        return ResponseEntity.ok("");
    }

    @PatchMapping("/add-measurement-unit")
    public ResponseEntity<String> addMeasurementUnit(@RequestBody MeasurementUnit measurementUnit) {

        return ResponseEntity.ok("");
    }

    @PatchMapping("/add-measurement-units")
    public ResponseEntity<String> addMeasurementUnits(@RequestBody List<MeasurementUnit> measurementUnits) {

        return ResponseEntity.ok("");
    }


}
