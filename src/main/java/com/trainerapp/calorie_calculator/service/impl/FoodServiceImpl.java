package com.trainerapp.calorie_calculator.service.impl;

import com.trainerapp.calorie_calculator.dto.response.FoodResponseDto;
import com.trainerapp.calorie_calculator.dto.request.FoodRequestDto;
import com.trainerapp.calorie_calculator.dto.request.MeasurementUnitRequestDto;
import com.trainerapp.calorie_calculator.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.exception.DuplicatedMeasurementUnitException;
import com.trainerapp.calorie_calculator.exception.DuplicatedMicronutrientContentException;
import com.trainerapp.calorie_calculator.exception.FoodNotFoundException;
import com.trainerapp.calorie_calculator.mapper.*;
import com.trainerapp.calorie_calculator.dto.request.MicronutrientContentRequestDto;
import com.trainerapp.calorie_calculator.model.entity.Food;
import com.trainerapp.calorie_calculator.model.entity.MeasurementUnit;
import com.trainerapp.calorie_calculator.model.entity.Tag;
import com.trainerapp.calorie_calculator.repository.FoodRepository;
import com.trainerapp.calorie_calculator.service.FoodService;
import com.trainerapp.calorie_calculator.service.MeasurementUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;
    private final MeasurementUnitMapper measurementUnitMapper;
    private final MeasurementUnitService measurementUnitService;
    private final NutritionalInfoMapper nutritionalInfoMapper;
    private final TagServiceImpl tagServiceImpl;
    private final MicronutrientContentMapper micronutrientContentMapper;

    @Override
    public List<FoodResponseDto> getAll() {
        return foodRepository.findAll()
                .stream()
                .map(foodMapper::toDto)
                .toList();
    }

    @Override
    public FoodResponseDto getById(long id) {
        return foodMapper.toDto(foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id)));
    }

    @Override
    public FoodResponseDto save(FoodRequestDto food) {
        return foodMapper.toDto(foodRepository.save(foodMapper.toEntity(food)));
    }

    @Override
    public void deleteById(long id) {
        foodRepository.deleteById(id);
    }

    @Override
    public FoodResponseDto update(Long id, FoodRequestDto updatedFood) {

        Food existingFood = this.findEntityById(id);

        foodMapper.updateFoodFromDto(updatedFood, existingFood);

        return foodMapper.toDto(existingFood);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FoodResponseDto> findByCaloriesBetween(Integer calories1, Integer calories2) {
        int min = Objects.requireNonNullElse(calories1, 0);
        int max = Objects.requireNonNullElse(calories2, Integer.MAX_VALUE);

        return foodRepository.findByNutritionalInfo_EnergyValueBetween(min, max)
                .stream()
                .map(foodMapper::toDto)
                .toList();
    }

    @Override
    public List<FoodResponseDto> findByFoodOrigin(FoodOriginType foodOriginType) {
        return foodRepository.findByFoodOrigin(foodOriginType)
                .stream()
                .map(foodMapper::toDto)
                .toList();
    }

    @Override
    public FoodResponseDto addMicronutrient(
            Long id, MicronutrientContentRequestDto micronutrientContent) {
        Food existingFood = this.findEntityById(id);

        verifyMicronutrientAlreadyAssigned(existingFood, micronutrientContent.micronutrientId());
        existingFood.getMicronutrients().add(micronutrientContentMapper.fromDataDto(micronutrientContent));

        return foodMapper.toDto(foodRepository.save(existingFood));
    }

    @Override
    public FoodResponseDto addOrUpdateMicronutrients(
            Long id, List<MicronutrientContentRequestDto> micronutrientContents) {
        Food existingFood = this.findEntityById(id);

        for (MicronutrientContentRequestDto content : micronutrientContents) {
            existingFood.getMicronutrients().removeIf(
                    m -> m.getMicronutrient().getId().equals(content.micronutrientId())
            );
            existingFood.getMicronutrients().add(micronutrientContentMapper.fromDataDto(content));
        }

        return foodMapper.toDto(foodRepository.save(existingFood));
    }

    @Override
    public void removeMicronutrient(Long foodId, Long micronutrientId) {
        Food existingFood = this.findEntityById(foodId);

        existingFood.getMicronutrients().removeIf(
                m -> m.getMicronutrient().getId().equals(micronutrientId)
        );

        foodRepository.save(existingFood);
    }

    @Override
    public void removeMicronutrients(Long foodId, List<Long> micronutrientIds) {
        Food existingFood = this.findEntityById(foodId);

        existingFood.getMicronutrients().removeIf(
                m -> micronutrientIds.contains(m.getMicronutrient().getId())
        );
        foodRepository.save(existingFood);
    }

    @Override
    public FoodResponseDto addMeasurementUnit(MeasurementUnitRequestDto unitDto) {
        Food existingFood = this.findEntityById(unitDto.foodId());

        if (existingFood.getMeasurementUnits().stream()
                .anyMatch(u -> u.getUnit().equals(unitDto.unit()))) {
            throw new DuplicatedMeasurementUnitException("Measurement unit already assigned to this food.");
        }

        MeasurementUnit newUnit = measurementUnitMapper.toEntity(unitDto);
        newUnit.setFood(existingFood);
        existingFood.getMeasurementUnits().add(newUnit);

        return foodMapper.toDto(foodRepository.save(existingFood));
    }

    @Override
    public FoodResponseDto addTags(Long foodId, List<TagRequestDto> tagsData) {
        Food existingFood = this.findEntityById(foodId);

        // Verifica si los tags existen, si no los crea
        List<Tag> tagsToAdd = tagsData.stream()
                .map(tagServiceImpl::findOrCreateByDataDto)
                .toList();

        // Añade los tags evitando duplicados
        tagsToAdd.forEach(tag -> {
            if (!existingFood.getTags().contains(tag)) {
                existingFood.getTags().add(tag);
            }
        });

        return foodMapper.toDto(foodRepository.save(existingFood));
    }

    @Override
    public FoodResponseDto removeTags(Long foodId, List<Long> tagIds) {
        Food existingFood = this.findEntityById(foodId);

        existingFood.getTags().removeIf(tag -> tagIds.contains(tag.getId()));

        return foodMapper.toDto(foodRepository.save(existingFood));
    }

    @Override
    public FoodResponseDto removeMeasurementUnit(Long unitId) {
        MeasurementUnit measurementUnit = measurementUnitService.findEntityById(unitId);
        measurementUnitService.deleteMeasurementUnit(measurementUnit.getId());

        Food existingFood = this.findEntityById(measurementUnit.getFood().getId());
        existingFood.getMeasurementUnits()
                .removeIf(measurementUnit1 -> measurementUnit1.getId().equals(unitId));
        return foodMapper.toDto(foodRepository.save(existingFood));
    }

    @Override
    public Food findEntityById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id));
    }

    private void verifyMicronutrientAlreadyAssigned(Food food, Long micronutrientId) {
        if (food.getMicronutrients().stream()
                .anyMatch(m -> m.getMicronutrient().getId().equals(micronutrientId))) {
            throw new DuplicatedMicronutrientContentException("Micronutrient already assigned to this food.");
        }
    }
}
