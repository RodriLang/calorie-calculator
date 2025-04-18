package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.enums.UnitType;
import com.trainerapp.calorie_calculator.mapper.SeasoningMapper;
import com.trainerapp.calorie_calculator.model.dto.SeasoningDto;
import com.trainerapp.calorie_calculator.model.dto.create.SeasoningDataDto;
import com.trainerapp.calorie_calculator.model.entity.Seasoning;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SeasoningMapperImplementation implements SeasoningMapper {

    public Seasoning fromDto(SeasoningDto seasoningDto){
        UnitType unitType = Arrays
                .stream(UnitType.values())
                .filter(u->u.getAbbreviation().equals(seasoningDto.unit()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return Seasoning.builder()
                .food(seasoningDto.food())
                .quantity(seasoningDto.quantity())
                .unit(unitType)
                .note(seasoningDto.note())
                .build();
    }

    public SeasoningDto toDto(Seasoning seasoning){
        return SeasoningDto.builder()
                .food(seasoning.getFood())
                .quantity(seasoning.getQuantity())
                .unit(seasoning.getUnit().getAbbreviation())
                .note(seasoning.getNote())
                .build();
    }

    public Seasoning fromDataDto(SeasoningDataDto seasoningDataDto){

        return Seasoning.builder()
                .food(seasoningDataDto.food())
                .quantity(seasoningDataDto.quantity())
                .unit(seasoningDataDto.unit())
                .note(seasoningDataDto.note())
                .build();
    }
}
