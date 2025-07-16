package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.response.NutritionalInfoResponseDto;
import com.trainerapp.calorie_calculator.dto.request.NutritionalInfoRequestDto;
import com.trainerapp.calorie_calculator.model.entity.NutritionalInfo;

public interface NutritionalInfoMapper {

    NutritionalInfo toEntity(NutritionalInfoResponseDto dto);

    NutritionalInfoResponseDto toDto(NutritionalInfo entity);

    NutritionalInfo fromDataDto(NutritionalInfoRequestDto dataDto);
}

