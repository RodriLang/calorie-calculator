package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.mapper.MicronutrientContentMapper;
import com.trainerapp.calorie_calculator.dto.MicronutrientContentDto;
import com.trainerapp.calorie_calculator.dto.create.MicronutrientContentDataDto;
import com.trainerapp.calorie_calculator.model.entity.Micronutrient;
import com.trainerapp.calorie_calculator.model.entity.MicronutrientContent;
import com.trainerapp.calorie_calculator.service.MicronutrientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MicronutrientContentMapperImplementation implements MicronutrientContentMapper {

    private final MicronutrientService micronutrientService;

    public MicronutrientContentDto toDto(MicronutrientContent micronutrientContent) {
        Double amountPerUnit = micronutrientContent.getAmountPerUnit();

        Double dailyPercentage = amountPerUnit
                    / micronutrientContent.getMicronutrient().getDailyAmount() * 100;


        return MicronutrientContentDto.builder()
                .name(micronutrientContent.getMicronutrient().getName())
                .dailyPercentage(dailyPercentage)
                .unit(micronutrientContent.getMicronutrient().getUnit().getAbbreviation())
                .type(micronutrientContent.getMicronutrient().getType())
                .build();
    }

    public MicronutrientContent fromDataDto(MicronutrientContentDataDto micronutrientContentDataDto) {
        Micronutrient micronutrient = micronutrientService.getEntityById(micronutrientContentDataDto.micronutrientId());

        return MicronutrientContent.builder()
                .micronutrient(micronutrient)
                .amountPerUnit(micronutrientContentDataDto.amountPerUnit())
                .build();
    }

    public MicronutrientContent toEntity(MicronutrientContentDto dto) {
        Micronutrient micronutrient = micronutrientService.getByName(dto.name()); // Asumiendo que tenés un método que obtiene la entidad por nombre


        return MicronutrientContent.builder()
                .micronutrient(micronutrient)
                .amountPerUnit(dto.dailyPercentage() * micronutrient.getDailyAmount() / 100)  // inversa del cálculo del toDto
                .build();
    }


}
