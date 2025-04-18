package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.SeasoningDto;
import com.trainerapp.calorie_calculator.model.dto.create.SeasoningDataDto;
import com.trainerapp.calorie_calculator.model.entity.Seasoning;

public interface SeasoningMapper {

    Seasoning fromDto(SeasoningDto seasoningDto);

    SeasoningDto toDto(Seasoning seasoning);

    Seasoning fromDataDto(SeasoningDataDto seasoningDataDto);
}
