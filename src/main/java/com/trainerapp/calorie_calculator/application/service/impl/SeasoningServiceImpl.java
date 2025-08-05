package com.trainerapp.calorie_calculator.application.service.impl;

import com.trainerapp.calorie_calculator.domain.model.Seasoning;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.SeasoningEntity;
import com.trainerapp.calorie_calculator.infrastructure.persistence.mapper.SeasoningEntityMapper;
import com.trainerapp.calorie_calculator.web.dto.request.SeasoningRequestDto;
import com.trainerapp.calorie_calculator.domain.repository.SeasoningRepository;
import com.trainerapp.calorie_calculator.application.service.SeasoningService;
import com.trainerapp.calorie_calculator.web.mapper.SeasoningDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SeasoningServiceImpl implements SeasoningService {

    private final SeasoningRepository seasoningRepository;
    private final MeasurementUnitServiceImpl measurementUnitServiceImpl;
    private final SeasoningEntityMapper seasoningEntityMapper;
    private final SeasoningDtoMapper seasoningDtoMapper;


    @Override
    public Seasoning create(SeasoningRequestDto seasoningRequestDto) {
        return seasoningDtoMapper.toModel(seasoningRequestDto);
    }

    @Override
    public void update(Seasoning seasoning, SeasoningRequestDto updateDto) {
        seasoningDtoMapper.updateFromDto(updateDto, seasoning.toBuilder());
    }

    private Seasoning save(Seasoning seasoning) {
        SeasoningEntity seasoningEntity = seasoningEntityMapper.toEntity(seasoning);
        return seasoningEntityMapper.toModel(seasoningRepository.save(seasoningEntity));
    }

}
