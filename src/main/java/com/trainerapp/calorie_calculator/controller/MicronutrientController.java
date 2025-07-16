package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.response.MicronutrientResponseDto;
import com.trainerapp.calorie_calculator.dto.request.MicronutrientRequestDto;
import com.trainerapp.calorie_calculator.service.MicronutrientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calorie-calculator/micronutrients")
public class MicronutrientController {

    private final MicronutrientService micronutrientService;

    @PostMapping
    public ResponseEntity<MicronutrientResponseDto> save(@RequestBody MicronutrientRequestDto micronutrientData) {
        return ResponseEntity.ok(micronutrientService.createMicronutrient(micronutrientData));
    }

    @GetMapping
    public ResponseEntity<List<MicronutrientResponseDto>> getAll() {
        return ResponseEntity.ok(micronutrientService.getAllMicronutrients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MicronutrientResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(micronutrientService.getMicronutrientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MicronutrientResponseDto> update(@PathVariable Long id,
                                                           @RequestBody MicronutrientRequestDto micronutrientData) {
        return ResponseEntity.ok(micronutrientService.update(id, micronutrientData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        micronutrientService.deleteMicronutrientById(id);
        return ResponseEntity.noContent().build();
    }
}