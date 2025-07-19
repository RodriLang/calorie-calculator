package com.trainerapp.calorie_calculator.service.impl;

import com.trainerapp.calorie_calculator.dto.request.MicronutrientRequestDto;
import com.trainerapp.calorie_calculator.dto.response.MicronutrientResponseDto;
import com.trainerapp.calorie_calculator.exception.MicronutrientNotFoundException;
import com.trainerapp.calorie_calculator.mapper.MicronutrientMapper;
import com.trainerapp.calorie_calculator.model.entity.Micronutrient;
import com.trainerapp.calorie_calculator.repository.MicronutrientRepository;
import com.trainerapp.calorie_calculator.service.MicronutrientService; // Importar la interfaz
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MicronutrientServiceImpl implements MicronutrientService { // Implementar la interfaz

    private final MicronutrientRepository micronutrientRepository;
    private final MicronutrientMapper micronutrientMapper;

    @Override
    public Micronutrient getByName(String name) {
        return micronutrientRepository.findByName(name)
                .orElseThrow(() -> new MicronutrientNotFoundException(name));
    }

    @Override
    public List<Micronutrient> getMicronutrients() {
        return micronutrientRepository.findAll();
    }

    @Override
    public MicronutrientResponseDto createMicronutrient(MicronutrientRequestDto micronutrientRequestDto) {
        return micronutrientMapper.toDto(micronutrientRepository.save(micronutrientMapper.fromDataDto(micronutrientRequestDto)));
    }

    @Override
    public MicronutrientResponseDto getMicronutrientById(Long id) {
        return micronutrientMapper.toDto(micronutrientRepository.findById(id)
                .orElseThrow(() -> new MicronutrientNotFoundException(id)));
    }

    @Override
    public Micronutrient getEntityById(Long id) {
        return micronutrientRepository.findById(id)
                .orElseThrow(() -> new MicronutrientNotFoundException(id));
    }

    @Override
    public List<MicronutrientResponseDto> getAllMicronutrients() {
        return micronutrientRepository.findAll()
                .stream()
                .map(micronutrientMapper::toDto)
                .toList();
    }

    @Override
    public void deleteMicronutrientById(Long id) {
        if (micronutrientRepository.existsById(id)) {
            micronutrientRepository.deleteById(id);
        } else {
            throw new MicronutrientNotFoundException(id);
        }
    }

    @Override
    public MicronutrientResponseDto update(Long id, MicronutrientRequestDto micronutrientRequestDto) {
        Micronutrient existingMicronutrient = micronutrientRepository.findById(id)
                .orElseThrow(() -> new MicronutrientNotFoundException(id));

        existingMicronutrient.setName(micronutrientRequestDto.name());
        existingMicronutrient.setUnit(micronutrientRequestDto.unit());
        existingMicronutrient.setType(micronutrientRequestDto.type());
        existingMicronutrient.setDailyAmount(micronutrientRequestDto.dailyAmount());

        return micronutrientMapper.toDto(micronutrientRepository.save(existingMicronutrient));
    }
}
