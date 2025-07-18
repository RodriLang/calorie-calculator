package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.dto.response.FoodResponseDto;
import com.trainerapp.calorie_calculator.dto.request.FoodRequestDto;
import com.trainerapp.calorie_calculator.dto.request.MeasurementUnitRequestDto;
import com.trainerapp.calorie_calculator.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.exception.DuplicatedMicronutrientContentException;
import com.trainerapp.calorie_calculator.exception.FoodNotFoundException;
import com.trainerapp.calorie_calculator.mapper.*;
import com.trainerapp.calorie_calculator.dto.request.MicronutrientContentRequestDto;
import com.trainerapp.calorie_calculator.dto.update.FoodUpdateDto;
import com.trainerapp.calorie_calculator.model.entity.Food;
import com.trainerapp.calorie_calculator.model.entity.MeasurementUnit;
import com.trainerapp.calorie_calculator.model.entity.Tag;
import com.trainerapp.calorie_calculator.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class FoodService {

    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;
    private final NutritionalInfoMapper nutritionalInfoMapper;
    private final TagService tagService;
    private final MicronutrientContentMapper micronutrientContentMapper;


    public List<FoodResponseDto> getAll() {
        return foodRepository.findAll()
                .stream()
                .map(foodMapper::toDto)
                .toList();
    }

    public FoodResponseDto getById(long id) {
        return foodMapper.toDto(foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id)));
    }

    public FoodResponseDto save(FoodRequestDto food) {
        return foodMapper.toDto(foodRepository.save(foodMapper.toEntity(food)));
    }

    public void deleteById(long id) {
        foodRepository.deleteById(id);
    }

    public FoodResponseDto update(Long id, FoodRequestDto updatedFood) {
        Food existingFood = foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id));

        existingFood.setName(updatedFood.name());
        existingFood.setFoodOrigin(updatedFood.foodOrigin());
        existingFood.setNutritionalFunctions(updatedFood.nutritionalFunctions());

        existingFood.setNutritionalInfo(nutritionalInfoMapper
                .fromDataDto(updatedFood.nutritionalInfo()));

        existingFood.setMicronutrients(updatedFood.micronutrients()
                .stream()
                .map(micronutrientContentMapper::fromDataDto)
                .toList());

        existingFood.setTags(updatedFood.tags()
                .stream()
                .map(tagService::findOrCreateByDataDto)
                .toList());

        return foodMapper.toDto(foodRepository.save(existingFood));
    }

    public FoodResponseDto parcialUpdate(Long id, FoodUpdateDto updatedFood) {
        Food existingFood = foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id));

        updatedFood.name().ifPresent(existingFood::setName);
        updatedFood.foodOrigin().ifPresent(existingFood::setFoodOrigin);
        updatedFood.nutritionalFunctions().ifPresent(existingFood::setNutritionalFunctions);

        updatedFood.nutritionalInfo()
                .ifPresent(n -> existingFood.setNutritionalInfo(
                        nutritionalInfoMapper.fromDataDto(n)));

        updatedFood.micronutrients()
                .ifPresent(m -> existingFood.setMicronutrients(m
                        .stream()
                        .map(micronutrientContentMapper::fromDataDto)
                        .toList()));

        updatedFood.tags()
                .ifPresent(t -> existingFood.setTags(t
                        .stream()
                        .map(tagService::findOrCreateByDataDto)
                        .toList()));

       return foodMapper.toDto(foodRepository.save(existingFood));
    }

    @Transactional(readOnly = true)
    public List<FoodResponseDto> findByCaloriesBetween(Integer calories1, Integer calories2) {
        int min = Objects.requireNonNullElse(calories1, 0);
        int max = Objects.requireNonNullElse(calories2, Integer.MAX_VALUE);

        return foodRepository.findByNutritionalInfo_EnergyValueBetween(min, max)
                .stream()
                .map(foodMapper::toDto)
                .toList();
    }


    public List<FoodResponseDto> findByFoodOrigin(FoodOriginType foodOriginType) {
        return foodRepository.findByFoodOrigin(foodOriginType)
                .stream()
                .map(foodMapper::toDto)
                .toList();
    }

    public FoodResponseDto addMicronutrient(Long id, MicronutrientContentRequestDto micronutrientContent) {
        Food existingFood = foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id));

        if (isMicronutrientAlreadyAssigned(existingFood, micronutrientContent.micronutrientId())) {
            throw new DuplicatedMicronutrientContentException("Micronutrient already assigned to this food.");
        }

        existingFood.getMicronutrients().add(micronutrientContentMapper.fromDataDto(micronutrientContent));

        return foodMapper.toDto(foodRepository.save(existingFood));
    }

    public FoodResponseDto addOrUpdateMicronutrients(Long id, List<MicronutrientContentRequestDto> micronutrientContents) {
        Food existingFood = foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id));

        for (MicronutrientContentRequestDto content : micronutrientContents) {
            existingFood.getMicronutrients().removeIf(
                    m -> m.getMicronutrient().getId().equals(content.micronutrientId())
            );
            existingFood.getMicronutrients().add(micronutrientContentMapper.fromDataDto(content));
        }

        return foodMapper.toDto(foodRepository.save(existingFood));
    }

    public FoodResponseDto removeMicronutrients(Long foodId, List<Long> micronutrientIds) {
        Food existingFood = foodRepository.findById(foodId)
                .orElseThrow(() -> new FoodNotFoundException(foodId));

        existingFood.getMicronutrients().removeIf(
                m -> micronutrientIds.contains(m.getMicronutrient().getId())
        );

        return foodMapper.toDto(foodRepository.save(existingFood));
    }


    public FoodResponseDto addMeasurementUnit(Long foodId, MeasurementUnitRequestDto unitDto) {
        Food existingFood = foodRepository.findById(foodId)
                .orElseThrow(() -> new FoodNotFoundException(foodId));

        boolean alreadyAssigned = existingFood.getMeasurementUnits().stream()
                .anyMatch(u -> u.getUnit().equals(unitDto.unit()));

        if (!alreadyAssigned) {
            MeasurementUnit newUnit = new MeasurementUnit();
            newUnit.setUnit(unitDto.unit());
            newUnit.setGramsPerUnit(unitDto.gramsPerUnit());
            newUnit.setFood(existingFood);  // vinculación correcta
            existingFood.getMeasurementUnits().add(newUnit);
        }

        return foodMapper.toDto(foodRepository.save(existingFood));
    }


    public FoodResponseDto addTags(Long foodId, List<TagRequestDto> tagsData) {
        Food existingFood = foodRepository.findById(foodId)
                .orElseThrow(() -> new FoodNotFoundException(foodId));

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

        return foodMapper.toDto(foodRepository.save(existingFood));
    }


    public FoodResponseDto removeTags(Long foodId, List<Long> tagIds) {
        Food existingFood = foodRepository.findById(foodId)
                .orElseThrow(() -> new FoodNotFoundException(foodId));

        existingFood.getTags().removeIf(tag -> tagIds.contains(tag.getId()));

        return foodMapper.toDto(foodRepository.save(existingFood));
    }

    public FoodResponseDto removeMeasurementUnit(Long foodId, Long unitId) {
        Food existingFood = foodRepository.findById(foodId)
                .orElseThrow(() -> new FoodNotFoundException(foodId));

        existingFood.getTags().removeIf(tag -> tag.getId().equals(unitId));

        return foodMapper.toDto(foodRepository.save(existingFood));
    }

    public Food findEntityById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id));
    }

    private boolean isMicronutrientAlreadyAssigned(Food food, Long micronutrientId) {
        return food.getMicronutrients().stream()
                .anyMatch(m -> m.getMicronutrient().getId().equals(micronutrientId));
    }
}
