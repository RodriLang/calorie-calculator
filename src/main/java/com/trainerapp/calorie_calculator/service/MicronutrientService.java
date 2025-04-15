package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.exception.MicronutrientNotFoundException;
import com.trainerapp.calorie_calculator.mapper.MicronutrientMapper;
import com.trainerapp.calorie_calculator.model.dto.MicronutrientContentDto;
import com.trainerapp.calorie_calculator.model.dto.MicronutrientDto;
import com.trainerapp.calorie_calculator.model.dto.create.MicronutrientDataDto;
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

    public MicronutrientDto createMicronutrient(MicronutrientDataDto micronutrientDataDto) {
        return micronutrientMapper
                .toDto(micronutrientRepository
                        .save(micronutrientMapper
                                .fromDataDto(micronutrientDataDto)));
    }

    public MicronutrientDto getMicronutrientById(Long id) {
        return micronutrientMapper.toDto(micronutrientRepository.findById(id)
                .orElseThrow(() -> new MicronutrientNotFoundException(id)));
    }

    public Micronutrient getEntityById(Long id) {
        return micronutrientRepository.findById(id)
                .orElseThrow(() -> new MicronutrientNotFoundException(id));
    }

    public List<MicronutrientDto> getAllMicronutrients() {
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

    public MicronutrientDto update(Long id, MicronutrientDataDto micronutrientDataDto) {
        Micronutrient existingMicronutrient = micronutrientRepository.findById(id)
                .orElseThrow(() -> new MicronutrientNotFoundException(id));

        existingMicronutrient.setName(micronutrientDataDto.name());
        existingMicronutrient.setUnit(micronutrientDataDto.unit());
        existingMicronutrient.setType(micronutrientDataDto.type());
        existingMicronutrient.setDailyAmount(micronutrientDataDto.dailyAmount());

        return micronutrientMapper.toDto(micronutrientRepository.save(existingMicronutrient));
    }
}
