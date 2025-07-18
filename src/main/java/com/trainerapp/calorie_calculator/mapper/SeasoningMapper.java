package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.request.SeasoningRequestDto;
import com.trainerapp.calorie_calculator.dto.response.SeasoningResponseDto;
import com.trainerapp.calorie_calculator.model.entity.Seasoning;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeasoningMapper {

    SeasoningResponseDto toDto(Seasoning seasoning);

    Seasoning fromDataDto(SeasoningRequestDto seasoningRequestDto);

}
