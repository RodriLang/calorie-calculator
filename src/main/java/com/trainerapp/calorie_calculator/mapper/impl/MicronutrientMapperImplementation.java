package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.enums.UnitType;
import com.trainerapp.calorie_calculator.mapper.MicronutrientMapper;
import com.trainerapp.calorie_calculator.model.dto.MicronutrientDto;
import com.trainerapp.calorie_calculator.model.dto.create.MicronutrientDataDto;
import com.trainerapp.calorie_calculator.model.entity.Micronutrient;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MicronutrientMapperImplementation implements MicronutrientMapper {

    public Micronutrient fromDto(MicronutrientDto micronutrientDto) {
        UnitType unitType = Arrays
                .stream(UnitType.values())
                .filter(u->u.getAbbreviation().equals(micronutrientDto.unit()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return Micronutrient.builder()
                .id(micronutrientDto.id())
                .name(micronutrientDto.name())
                .type(micronutrientDto.type())
                .dailyAmount(micronutrientDto.dailyAmount())
                .unit(unitType)
                .build();
    }

    public MicronutrientDto toDto(Micronutrient micronutrient) {
        return MicronutrientDto.builder()
                .id(micronutrient.getId())
                .name(micronutrient.getName())
                .dailyAmount(micronutrient.getDailyAmount())
                .unit(micronutrient.getUnit().getAbbreviation())
                .type(micronutrient.getType())
                .build();
    }

    public Micronutrient fromDataDto(MicronutrientDataDto micronutrientDataDto) {
        return Micronutrient.builder()
                .name(micronutrientDataDto.name())
                .type(micronutrientDataDto.type())
                .dailyAmount(micronutrientDataDto.dailyAmount())
                .unit(micronutrientDataDto.unit())
                .build();
    }
}
