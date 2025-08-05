package com.trainerapp.calorie_calculator.web.controller;

import com.trainerapp.calorie_calculator.web.dto.response.FoodResponseDto;
import com.trainerapp.calorie_calculator.web.dto.request.MeasurementUnitRequestDto;
import com.trainerapp.calorie_calculator.application.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calorie-calculator/measurement-units")
public class FoodMeasurementUnitController {

    private final FoodService foodService;


    @PutMapping()
    public ResponseEntity<FoodResponseDto> addMeasurementUnit(
            @RequestBody MeasurementUnitRequestDto measurementUnit) {
        return ResponseEntity.ok(foodService.addMeasurementUnit(measurementUnit));
    }


    @DeleteMapping()
    public ResponseEntity<FoodResponseDto> removeMeasurementUnit(
            @RequestBody UUID measurementUnitId) {
        return ResponseEntity.ok(foodService.removeMeasurementUnit(measurementUnitId));
    }
}
