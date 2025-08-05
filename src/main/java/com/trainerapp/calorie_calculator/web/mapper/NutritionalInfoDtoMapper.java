package com.trainerapp.calorie_calculator.web.mapper;

import com.trainerapp.calorie_calculator.domain.model.NutritionalInfo;
import com.trainerapp.calorie_calculator.web.dto.request.NutritionalInfoRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.NutritionalInfoResponseDto;

public interface NutritionalInfoDtoMapper {

    NutritionalInfoResponseDto toDto(NutritionalInfo model);

    NutritionalInfo toModel(NutritionalInfoRequestDto dto);

    NutritionalInfo updateFromDto(NutritionalInfoRequestDto dto, NutritionalInfo model);
}

