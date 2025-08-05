package com.trainerapp.calorie_calculator.web.mapper;

import com.trainerapp.calorie_calculator.domain.model.MicronutrientContent;
import com.trainerapp.calorie_calculator.web.dto.request.MicronutrientContentRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.MicronutrientContentResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MicronutrientContentDtoMapper {


    MicronutrientContentResponseDto toDto(MicronutrientContent model);

    MicronutrientContent toModel(MicronutrientContentRequestDto dto);
}