package com.trainerapp.calorie_calculator.application.service;

import com.trainerapp.calorie_calculator.domain.model.Micronutrient;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.MicronutrientEntity;
import com.trainerapp.calorie_calculator.web.dto.request.MicronutrientRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.MicronutrientResponseDto;

import java.util.List;

public interface MicronutrientService {
    Micronutrient getModelByName(String name);
    Micronutrient getModelById(Long id);
    List<MicronutrientEntity> getMicronutrients();
    MicronutrientResponseDto createMicronutrient(MicronutrientRequestDto micronutrientRequestDto);
    MicronutrientResponseDto getMicronutrientById(Long id);
    MicronutrientEntity getEntityById(Long id);
    List<MicronutrientResponseDto> getAllMicronutrients();
    void deleteMicronutrientById(Long id);
    MicronutrientResponseDto update(Long id, MicronutrientRequestDto micronutrientRequestDto);
}
