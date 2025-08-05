package com.trainerapp.calorie_calculator.application.service.impl;

import com.trainerapp.calorie_calculator.application.service.MicronutrientService;
import com.trainerapp.calorie_calculator.application.service.TagService;
import com.trainerapp.calorie_calculator.domain.enums.UnitType;
import com.trainerapp.calorie_calculator.domain.model.*;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.FoodEntity;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.MeasurementUnitEntity;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.MicronutrientEntity;
import com.trainerapp.calorie_calculator.web.dto.response.FoodResponseDto;
import com.trainerapp.calorie_calculator.web.dto.request.FoodRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.MeasurementUnitRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.domain.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.application.exception.DuplicatedMeasurementUnitException;
import com.trainerapp.calorie_calculator.application.exception.DuplicatedMicronutrientContentException;
import com.trainerapp.calorie_calculator.application.exception.FoodNotFoundException;
import com.trainerapp.calorie_calculator.infrastructure.persistence.mapper.FoodEntityMapper;
import com.trainerapp.calorie_calculator.infrastructure.persistence.mapper.MeasurementUnitEntityMapper;
import com.trainerapp.calorie_calculator.infrastructure.persistence.mapper.NutritionalInfoEntityMapper;
import com.trainerapp.calorie_calculator.web.dto.request.MicronutrientContentRequestDto;
import com.trainerapp.calorie_calculator.domain.repository.FoodRepository;
import com.trainerapp.calorie_calculator.application.service.FoodService;
import com.trainerapp.calorie_calculator.application.service.MeasurementUnitService;
import com.trainerapp.calorie_calculator.web.mapper.FoodDtoMapper;
import com.trainerapp.calorie_calculator.web.mapper.MeasurementUnitDtoMapper;
import com.trainerapp.calorie_calculator.web.mapper.MicronutrientContentDtoMapper;
import com.trainerapp.calorie_calculator.web.mapper.MicronutrientDtoMapper;
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
    private final FoodEntityMapper foodEntityMapper;
    private final FoodDtoMapper foodDtoMapper;
    private final MeasurementUnitEntityMapper measurementUnitEntityMapper;
    private final MeasurementUnitDtoMapper measurementUnitDtoMapper;
    private final MeasurementUnitService measurementUnitService;
    private final MicronutrientService micronutrientService;
    private final MicronutrientContentDtoMapper micronutrientContentDtoMapper;
    private final NutritionalInfoEntityMapper nutritionalInfoEntityMapper;
    private final TagService tagService;

    @Override
    public List<FoodResponseDto> getAll() {
        return foodRepository.findAll()
                .stream()
                .map(foodEntityMapper::toModel)
                .map(foodDtoMapper::toDto)
                .toList();
    }

    @Override
    public FoodResponseDto getById(long id) {
        return foodDtoMapper.toDto(this.findModelById(id));
    }

    @Override
    public FoodResponseDto create(FoodRequestDto food) {
        Food newFood = foodDtoMapper.toModel(food);

        return foodDtoMapper.toDto(this.saveFood(newFood));
    }

    @Override
    public void deleteById(long id) {
        foodRepository.deleteById(id);
    }

    @Override
    public FoodResponseDto update(Long id, FoodRequestDto foodRequestDto) {

        Food existingFood = this.findModelById(id);
        Food updatedFood = foodDtoMapper.updateFoodFromDto(foodRequestDto, existingFood.toBuilder());

        return foodDtoMapper.toDto(this.saveFood(updatedFood));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FoodResponseDto> findByCaloriesBetween(Integer calories1, Integer calories2) {
        int min = Objects.requireNonNullElse(calories1, 0);
        int max = Objects.requireNonNullElse(calories2, Integer.MAX_VALUE);

        return foodRepository.findByNutritionalInfo_EnergyValueBetween(min, max)
                .stream()
                .map(foodEntityMapper::toModel)
                .map(foodDtoMapper::toDto)
                .toList();
    }

    @Override
    public List<FoodResponseDto> findByFoodOrigin(FoodOriginType foodOriginType) {
        return foodRepository.findByFoodOrigin(foodOriginType)
                .stream()
                .map(foodEntityMapper::toModel)
                .map(foodDtoMapper::toDto).toList();
    }

    @Override
    public FoodResponseDto addMicronutrient(
            Long id, MicronutrientContentRequestDto micronutrientContent) {
        Food existingFood = this.findModelById(id);

        MicronutrientEntity micronutrient = micronutrientService.getEntityById(micronutrientContent.micronutrientId());

        verifyMicronutrientAlreadyAssigned(existingFood, micronutrient.getName());
        existingFood.getMicronutrients().add(micronutrientContentDtoMapper.toModel(micronutrientContent));

        return foodDtoMapper.toDto(this.saveFood(existingFood));
    }

    @Override
    public FoodResponseDto addOrUpdateMicronutrients(
            Long id, List<MicronutrientContentRequestDto> micronutrientContents) {
        Food existingFood = this.findModelById(id);

        for (MicronutrientContentRequestDto content : micronutrientContents) {
            Micronutrient micronutrientAdded = micronutrientService.getModelById(content.micronutrientId());
            existingFood.getMicronutrients().removeIf(
                    m -> m.getMicronutrient().equals(micronutrientAdded)
            );

            existingFood.getMicronutrients().add(micronutrientContentDtoMapper.toModel(content));
        }

        return foodDtoMapper.toDto(this.saveFood(existingFood));
    }

    @Override
    public void removeMicronutrient(Long foodId, Long micronutrientId) {
        Food existingFood = this.findModelById(foodId);

        Micronutrient micronutrientAdded = micronutrientService.getModelById(micronutrientId);

        existingFood.getMicronutrients().removeIf(
                m -> m.getMicronutrient().equals(micronutrientAdded)
        );

        this.saveFood(existingFood);
    }

    @Override
    public void removeMicronutrients(Long foodId, List<Long> micronutrientIds) {
        Food existingFood = this.findModelById(foodId);

        List<Micronutrient> micronutrientsToRemove = micronutrientIds.stream()
                .map(micronutrientService::getModelById)
                .toList();

        existingFood.getMicronutrients().removeIf(
                content -> micronutrientsToRemove.contains(content.getMicronutrient())
        );

        this.saveFood(existingFood);
    }


    @Override
    public FoodResponseDto addMeasurementUnit(MeasurementUnitRequestDto unitDto) {
        Food existingFood = this.findModelById(unitDto.foodId());

        verifyMeasurementUnitAlreadyAssigned(existingFood, unitDto.unit());

        MeasurementUnit newUnit = MeasurementUnit.builder()
                .unit(unitDto.unit())
                .gramsPerUnit(unitDto.gramsPerUnit())
                .food(existingFood)
                .build();

        existingFood.getMeasurementUnits().add(newUnit);

        return foodDtoMapper.toDto(this.saveFood(existingFood));
    }

    @Override
    public FoodResponseDto addTags(Long foodId, List<TagRequestDto> tagsData) {
        Food existingFood = this.findModelById(foodId);

        // Verifica si los tags existen, si no los crea
        List<Tag> tagsToAdd = tagsData.stream()
                .map(tagService::findOrCreateByDataDto)
                .toList();

        // Añade los tags evitando duplicados
        tagsToAdd.forEach(tag -> {
            if (!existingFood.getTags().contains(tag)) {
                existingFood.getTags().add(tag);
            }
        });

        return foodDtoMapper.toDto(this.saveFood(existingFood));
    }

    @Override
    public FoodResponseDto removeTags(Long foodId, List<Long> tagIds) {
        Food existingFood = this.findModelById(foodId);

        List<Tag> removedTags = tagIds
                .stream()
                .map(tagService::findById)
                .toList();

        existingFood.getTags().removeIf(removedTags::contains);

        return foodDtoMapper.toDto(this.saveFood(existingFood));
    }

    @Override
    public FoodResponseDto removeMeasurementUnit(Long unitId) {
        MeasurementUnitEntity measurementUnit = measurementUnitService.findEntityById(unitId);
        measurementUnitService.deleteMeasurementUnit(measurementUnit.getId());

        Food existingFood = this.findModelById(measurementUnit.getFood().getId());
        existingFood.getMeasurementUnits()
                .removeIf(measurementUnit1
                        -> measurementUnit1.equals(measurementUnitEntityMapper.toModel(measurementUnit)));
        return foodDtoMapper.toDto(this.saveFood(existingFood));
    }

    @Override
    public Food findModelById(Long id) {
        return foodEntityMapper.toModel(foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id)));
    }

    private void verifyMicronutrientAlreadyAssigned(Food food, String micronutrientName) {
        if (food.getMicronutrients().stream()
                .anyMatch(m -> m.getMicronutrient().getName().equalsIgnoreCase(micronutrientName))) {
            throw new DuplicatedMicronutrientContentException("Micronutrient already assigned to this food.");
        }
    }

    private void verifyMeasurementUnitAlreadyAssigned(Food food, UnitType unitType) {
        if (food.getMeasurementUnits().stream()
                .anyMatch(u -> u.getUnit().equals(unitType))) {
            throw new DuplicatedMeasurementUnitException("Measurement unit already assigned to this food.");
        }
    }


    private Food saveFood(Food food) {

        FoodEntity foodEntity = foodRepository.save(foodEntityMapper.toEntity(food));
        return foodEntityMapper.toModel(foodEntity);
    }
}
