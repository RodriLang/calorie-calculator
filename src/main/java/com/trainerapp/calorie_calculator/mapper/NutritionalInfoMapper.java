package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.NutritionalInfoDto;
import com.trainerapp.calorie_calculator.dto.create.NutritionalInfoDataDto;
import com.trainerapp.calorie_calculator.model.entity.NutritionalInfo;

public interface NutritionalInfoMapper {

    NutritionalInfo toEntity(NutritionalInfoDto dto);


    NutritionalInfoDto toDto(NutritionalInfo entity);


    NutritionalInfo fromDataDto(NutritionalInfoDataDto dataDto);


}

