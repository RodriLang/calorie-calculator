package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.dto.response.MicronutrientResponseDto;
import com.trainerapp.calorie_calculator.dto.request.MicronutrientRequestDto;
import com.trainerapp.calorie_calculator.exception.MicronutrientNotFoundException;
import com.trainerapp.calorie_calculator.mapper.MicronutrientMapper;
import com.trainerapp.calorie_calculator.model.entity.Micronutrient;
import com.trainerapp.calorie_calculator.repository.MicronutrientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MicronutrientService {

    private final MicronutrientRepository micronutrientRepository;
    private final MicronutrientMapper micronutrientMapper;

    public Micronutrient getByName(String name) {
        return micronutrientRepository.findByName(name).orElseThrow(()->new MicronutrientNotFoundException(name));
    }

    public List<Micronutrient> getMicronutrients() {
        return micronutrientRepository.findAll();
    }

    public MicronutrientResponseDto createMicronutrient(MicronutrientRequestDto micronutrientRequestDto) {
        return micronutrientMapper
                .toDto(micronutrientRepository
                        .save(micronutrientMapper
                                .fromDataDto(micronutrientRequestDto)));
    }

    public MicronutrientResponseDto getMicronutrientById(Long id) {
        return micronutrientMapper.toDto(micronutrientRepository.findById(id)
                .orElseThrow(() -> new MicronutrientNotFoundException(id)));
    }

    public Micronutrient getEntityById(Long id) {
        return micronutrientRepository.findById(id)
                .orElseThrow(() -> new MicronutrientNotFoundException(id));
    }

    public List<MicronutrientResponseDto> getAllMicronutrients() {
        return micronutrientRepository.findAll()
                .stream()
                .map(micronutrientMapper::toDto)
                .toList();
    }

    public void deleteMicronutrientById(Long id) {
        if (micronutrientRepository.existsById(id)) {
            micronutrientRepository.deleteById(id);
        } else {
            throw new MicronutrientNotFoundException(id);
        }
    }

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
