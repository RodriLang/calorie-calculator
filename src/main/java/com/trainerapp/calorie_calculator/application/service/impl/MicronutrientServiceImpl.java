package com.trainerapp.calorie_calculator.application.service.impl;

import com.trainerapp.calorie_calculator.domain.model.Micronutrient;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.MicronutrientEntity;
import com.trainerapp.calorie_calculator.web.dto.request.MicronutrientRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.MicronutrientResponseDto;
import com.trainerapp.calorie_calculator.application.exception.MicronutrientNotFoundException;
import com.trainerapp.calorie_calculator.infrastructure.persistence.mapper.MicronutrientEntityMapper;
import com.trainerapp.calorie_calculator.domain.repository.MicronutrientRepository;
import com.trainerapp.calorie_calculator.application.service.MicronutrientService; // Importar la interfaz
import com.trainerapp.calorie_calculator.web.mapper.MicronutrientDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MicronutrientServiceImpl implements MicronutrientService { // Implementar la interfaz

    private final MicronutrientRepository micronutrientRepository;
    private final MicronutrientEntityMapper micronutrientEntityMapper;
    private final MicronutrientDtoMapper micronutrientDtoMapper;

    @Override
    public Micronutrient getModelByName(String name) {
        return micronutrientEntityMapper.toModel(micronutrientRepository.findByName(name)
                .orElseThrow(() -> new MicronutrientNotFoundException(name)));
    }

    @Override
    public List<MicronutrientEntity> getMicronutrients() {
        return micronutrientRepository.findAll();
    }

    @Override
    public MicronutrientResponseDto createMicronutrient(MicronutrientRequestDto micronutrientRequestDto) {
        Micronutrient micronutrient = micronutrientDtoMapper.toModel(micronutrientRequestDto);
        return micronutrientDtoMapper.toDto(this.save(micronutrient));
    }

    @Override
    public MicronutrientResponseDto getMicronutrientById(UUID id) {
        return micronutrientDtoMapper.toDto(this.getModelById(id));

    }

    @Override
    public MicronutrientEntity getEntityById(UUID id) {
        return micronutrientRepository.findByPublicId(id)
                .orElseThrow(() -> new MicronutrientNotFoundException(id));
    }

    @Override
    public List<MicronutrientResponseDto> getAllMicronutrients() {
        return micronutrientRepository.findAll()
                .stream()
                .map(micronutrientEntityMapper::toModel)
                .map(micronutrientDtoMapper::toDto)
                .toList();
    }

    @Override
    public void deleteMicronutrientById(UUID id) {

        MicronutrientEntity micronutrientEntity = getEntityById(id);

        micronutrientRepository.delete(micronutrientEntity);
    }

    @Override
    public MicronutrientResponseDto update(UUID id, MicronutrientRequestDto micronutrientRequestDto) {
        Micronutrient existingMicronutrient = getModelById(id);

        micronutrientDtoMapper.updateFromDto(micronutrientRequestDto, existingMicronutrient.toBuilder());

        return micronutrientDtoMapper.toDto(this.save(existingMicronutrient));
    }

    private Micronutrient save(Micronutrient micronutrient) {
        MicronutrientEntity entity = micronutrientEntityMapper.toEntity(micronutrient);
        return micronutrientEntityMapper.toModel(micronutrientRepository.save(entity));
    }

    @Override
    public Micronutrient getModelById(UUID id) {
        MicronutrientEntity existingMicronutrient = micronutrientRepository.findByPublicId(id)
                .orElseThrow(() -> new MicronutrientNotFoundException(id));
        return micronutrientEntityMapper.toModel(existingMicronutrient);
    }
}
