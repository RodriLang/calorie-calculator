package com.trainerapp.calorie_calculator.web.mapper;

import com.trainerapp.calorie_calculator.domain.model.Micronutrient;
import com.trainerapp.calorie_calculator.web.dto.request.MicronutrientRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.MicronutrientResponseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MicronutrientDtoMapper {

    MicronutrientResponseDto toDto(Micronutrient model);

    Micronutrient toModel(MicronutrientRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Micronutrient updateFromDto(MicronutrientRequestDto dto,@MappingTarget Micronutrient.MicronutrientBuilder builder);
}
