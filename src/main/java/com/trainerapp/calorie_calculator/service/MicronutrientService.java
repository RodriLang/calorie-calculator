package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.dto.request.MicronutrientRequestDto;
import com.trainerapp.calorie_calculator.dto.response.MicronutrientResponseDto;
import com.trainerapp.calorie_calculator.model.entity.Micronutrient;

import java.util.List;

public interface MicronutrientService {
    Micronutrient getByName(String name);
    List<Micronutrient> getMicronutrients();
    MicronutrientResponseDto createMicronutrient(MicronutrientRequestDto micronutrientRequestDto);
    MicronutrientResponseDto getMicronutrientById(Long id);
    Micronutrient getEntityById(Long id);
    List<MicronutrientResponseDto> getAllMicronutrients();
    void deleteMicronutrientById(Long id);
    MicronutrientResponseDto update(Long id, MicronutrientRequestDto micronutrientRequestDto);
}
