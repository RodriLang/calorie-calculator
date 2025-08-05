package com.trainerapp.calorie_calculator.application.service;

import com.trainerapp.calorie_calculator.domain.model.Micronutrient;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.MicronutrientEntity;
import com.trainerapp.calorie_calculator.web.dto.request.MicronutrientRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.MicronutrientResponseDto;

import java.util.List;
import java.util.UUID;

public interface MicronutrientService {
    Micronutrient getModelByName(String name);
    Micronutrient getModelById(UUID id);
    List<MicronutrientEntity> getMicronutrients();
    MicronutrientResponseDto createMicronutrient(MicronutrientRequestDto micronutrientRequestDto);
    MicronutrientResponseDto getMicronutrientById(UUID id);
    MicronutrientEntity getEntityById(UUID id);
    List<MicronutrientResponseDto> getAllMicronutrients();
    void deleteMicronutrientById(UUID id);
    MicronutrientResponseDto update(UUID id, MicronutrientRequestDto micronutrientRequestDto);
}
