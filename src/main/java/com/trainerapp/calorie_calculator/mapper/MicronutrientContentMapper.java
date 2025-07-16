package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.response.MicronutrientContentResponseDto;
import com.trainerapp.calorie_calculator.dto.request.MicronutrientContentRequestDto;
import com.trainerapp.calorie_calculator.model.entity.MicronutrientContent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MicronutrientContentMapper {


    MicronutrientContentResponseDto toDto(MicronutrientContent micronutrientContent);

    MicronutrientContent fromDataDto(MicronutrientContentRequestDto micronutrientContentRequestDto);
}