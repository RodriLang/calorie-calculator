package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.exception.DuplicatedMicronutrientContentException;
import com.trainerapp.calorie_calculator.exception.FoodNotFoundException;
import com.trainerapp.calorie_calculator.mapper.*;
import com.trainerapp.calorie_calculator.model.dto.FoodDto;
import com.trainerapp.calorie_calculator.model.dto.create.FoodDataDto;
import com.trainerapp.calorie_calculator.model.dto.create.MeasurementUnitDataDto;
import com.trainerapp.calorie_calculator.model.dto.create.MicronutrientContentDataDto;
import com.trainerapp.calorie_calculator.model.dto.create.TagDataDto;
import com.trainerapp.calorie_calculator.model.dto.update.FoodUpdateDto;
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


    public List<FoodDto> getAll() {
        return foodRepository.findAll()
                .stream()
                .map(foodMapper::toDto)
                .toList();
    }

    public FoodDto getById(long id) {
        return foodMapper.toDto(foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id)));
    }

    public FoodDto save(FoodDataDto food) {
        return foodMapper.toDto(foodRepository.save(foodMapper.fromDataDto(food)));
    }

    public void deleteById(long id) {
        foodRepository.deleteById(id);
    }

    public FoodDto update(Long id, FoodDataDto updatedFood) {
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

    public FoodDto parcialUpdate(Long id, FoodUpdateDto updatedFood) {
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
    public List<FoodDto> findByCaloriesBetween(Integer calories1, Integer calories2) {
        int min = Objects.requireNonNullElse(calories1, 0);
        int max = Objects.requireNonNullElse(calories2, Integer.MAX_VALUE);

        return foodRepository.findByNutritionalInfo_EnergyValueBetween(min, max)
                .stream()
                .map(foodMapper::toDto)
                .toList();
    }


    public List<FoodDto> findByFoodOrigin(FoodOriginType foodOriginType) {
        return foodRepository.findByFoodOrigin(foodOriginType)
                .stream()
                .map(foodMapper::toDto)
                .toList();
    }

    public FoodDto addMicronutrient(Long id, MicronutrientContentDataDto micronutrientContent) {
        Food existingFood = foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id));

        if (isMicronutrientAlreadyAssigned(existingFood, micronutrientContent.micronutrientId())) {
            throw new DuplicatedMicronutrientContentException("Micronutrient already assigned to this food.");
        }

        existingFood.getMicronutrients().add(micronutrientContentMapper.fromDataDto(micronutrientContent));

        return foodMapper.toDto(foodRepository.save(existingFood));
    }

    public FoodDto addOrUpdateMicronutrients(Long id, List<MicronutrientContentDataDto> micronutrientContents) {
        Food existingFood = foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id));

        for (MicronutrientContentDataDto content : micronutrientContents) {
            existingFood.getMicronutrients().removeIf(
                    m -> m.getMicronutrient().getId().equals(content.micronutrientId())
            );
            existingFood.getMicronutrients().add(micronutrientContentMapper.fromDataDto(content));
        }

        return foodMapper.toDto(foodRepository.save(existingFood));
    }

    public FoodDto removeMicronutrients(Long foodId, List<Long> micronutrientIds) {
        Food existingFood = foodRepository.findById(foodId)
                .orElseThrow(() -> new FoodNotFoundException(foodId));

        existingFood.getMicronutrients().removeIf(
                m -> micronutrientIds.contains(m.getMicronutrient().getId())
        );

        return foodMapper.toDto(foodRepository.save(existingFood));
    }


    public FoodDto addMeasurementUnit(Long foodId, MeasurementUnitDataDto unitDto) {
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


    public FoodDto addTags(Long foodId, List<TagDataDto> tagsData) {
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


    public FoodDto removeTags(Long foodId, List<Long> tagIds) {
        Food existingFood = foodRepository.findById(foodId)
                .orElseThrow(() -> new FoodNotFoundException(foodId));

        existingFood.getTags().removeIf(tag -> tagIds.contains(tag.getId()));

        return foodMapper.toDto(foodRepository.save(existingFood));
    }

    public FoodDto removeMeasurementUnit(Long foodId, Long unitId) {
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
