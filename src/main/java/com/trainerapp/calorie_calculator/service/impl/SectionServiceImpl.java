package com.trainerapp.calorie_calculator.service.impl;

import com.trainerapp.calorie_calculator.dto.request.SeasoningRequestDto;
import com.trainerapp.calorie_calculator.dto.request.IngredientRequestDto;
import com.trainerapp.calorie_calculator.dto.request.SectionRequestDto;
import com.trainerapp.calorie_calculator.dto.request.StepRequestDto;
import com.trainerapp.calorie_calculator.dto.response.SectionResponseDto;
import com.trainerapp.calorie_calculator.exception.CustomIngredientNotFoundException;
import com.trainerapp.calorie_calculator.exception.IngredientNotFoundException;
import com.trainerapp.calorie_calculator.exception.SectionNotFoundException;
import com.trainerapp.calorie_calculator.exception.StepNotFoundException;
import com.trainerapp.calorie_calculator.mapper.SectionMapper;
import com.trainerapp.calorie_calculator.mapper.StepMapper;
import com.trainerapp.calorie_calculator.model.entity.Seasoning;
import com.trainerapp.calorie_calculator.model.entity.Ingredient;
import com.trainerapp.calorie_calculator.model.entity.Section;
import com.trainerapp.calorie_calculator.model.entity.Step;
import com.trainerapp.calorie_calculator.repository.SectionRepository;
import com.trainerapp.calorie_calculator.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class SectionServiceImpl implements SectionService {


    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final StepMapper stepMapper;

    private final IngredientServiceImpl ingredientServiceImpl;
    private final SeasoningServiceImpl seasoningServiceImpl;


    @Override
    public List<SectionResponseDto> getSections() {
        return sectionRepository.findAll()
                .stream()
                .map(sectionMapper::toDto)
                .toList();
    }

    @Override
    public SectionResponseDto findById(Long sectionId) {
        return sectionMapper.toDto(this.findEntityById(sectionId));
    }

    @Override
    public Section findEntityById(Long sectionId) {
        return sectionRepository.findById(sectionId).orElseThrow(()
                -> new SectionNotFoundException(sectionId));
    }


    @Override
    public SectionResponseDto createRecipe(SectionRequestDto sectionRequestDto) {

        Section section = new Section();
        section.setName(sectionRequestDto.name());
        section.setDescription(sectionRequestDto.description());
        section.setPreparationTime(sectionRequestDto.preparationTime());
        section.setDifficulty(sectionRequestDto.difficulty());

        // Mapear ingredientes
        for (IngredientRequestDto ingredientDto : sectionRequestDto.ingredients()) {
            Ingredient ingredient = ingredientServiceImpl.create(ingredientDto);
            section.getIngredients().add(ingredient);
        }

        // Mapear custom ingredients
        for (SeasoningRequestDto customDto : sectionRequestDto.seasonings()) {
            Seasoning seasoning = seasoningServiceImpl.create(customDto);
            section.getSeasonings().add(seasoning);
        }

        // Guardar los pasos
        section.setSteps(sectionRequestDto.steps()
                .stream()
                .map(stepMapper::toEntity)
                .toList());

        section = sectionRepository.save(section);

        return sectionMapper.toDto(section);
    }


    @Override
    public SectionResponseDto updateRecipe(Long sectionId, SectionRequestDto sectionRequestDto) {

        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new SectionNotFoundException(sectionId));

        section.setName(sectionRequestDto.name());
        section.setDescription(sectionRequestDto.description());
        section.setPreparationTime(sectionRequestDto.preparationTime());
        section.setDifficulty(sectionRequestDto.difficulty());

        // Limpiar y actualizar ingredientes
        section.getIngredients().clear();
        for (IngredientRequestDto ingredientDto : sectionRequestDto.ingredients()) {
            Ingredient ingredient = ingredientServiceImpl.create(ingredientDto);
            section.getIngredients().add(ingredient);
        }

        // Limpiar y actualizar custom ingredients
        section.getSeasonings().clear();
        for (SeasoningRequestDto customDto : sectionRequestDto.seasonings()) {
            Seasoning seasoning = seasoningServiceImpl.create(customDto);
            section.getSeasonings().add(seasoning);
        }

        // Actualizar pasos
        section.getSteps().clear();
        section.getSteps().addAll(sectionRequestDto.steps()
                .stream()
                .map(stepMapper::toEntity)
                .toList());

        section = sectionRepository.save(section);

        return sectionMapper.toDto(section);
    }


    @Override
    public void deleteSection(Long sectionId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new SectionNotFoundException(sectionId));
        sectionRepository.delete(section);
    }


    @Override
    public SectionResponseDto addIngredientToRecipe(Long sectionId, IngredientRequestDto ingredientRequestDto) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new SectionNotFoundException(sectionId));

        Ingredient ingredient = ingredientServiceImpl.create(ingredientRequestDto);

        section.getIngredients().add(ingredient);
        section = sectionRepository.save(section);

        return sectionMapper.toDto(section);
    }

    @Override
    public SectionResponseDto removeIngredientFromRecipe(Long sectionId, Long ingredientId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new SectionNotFoundException(sectionId));

        boolean removed = section.getIngredients().removeIf(i -> i.getId().equals(ingredientId));

        if (!removed) {
            throw new IngredientNotFoundException("Ingredient not found with id: " + ingredientId + " in recipe id: " + sectionId);
        }

        section = sectionRepository.save(section);
        return sectionMapper.toDto(section);
    }


    @Override
    public SectionResponseDto updateIngredientInRecipe(Long recipeId, Long ingredientId, IngredientRequestDto newIngredientData) {
        Section section = sectionRepository.findById(recipeId)
                .orElseThrow(() -> new SectionNotFoundException(recipeId));

        Ingredient ingredient = section.getIngredients().stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst()
                .orElseThrow(() -> new IngredientNotFoundException(ingredientId));

        // Actualizar usando IngredientService
        ingredientServiceImpl.update(ingredient, newIngredientData);

        return sectionMapper.toDto(sectionRepository.save(section));
    }


//Seasonings

    @Override
    public SectionResponseDto addSeasoningToSection(Long recipeId, Long sectionId, SeasoningRequestDto seasoningRequestDto) {

        Section section = sectionRepository.findById(recipeId)
                .orElseThrow(() -> new SectionNotFoundException(recipeId));

        section.getSeasonings().add(seasoningServiceImpl.create(seasoningRequestDto));
        section = sectionRepository.save(section);

        return sectionMapper.toDto(section);
    }

    @Override
    public SectionResponseDto updateCustomIngredient(Long recipeId, Long customIngredientId, SeasoningRequestDto updatedData) {
        Section section = sectionRepository.findById(recipeId)
                .orElseThrow(() -> new SectionNotFoundException(recipeId));

        Seasoning ingredient = section.getSeasonings().stream()
                .filter(i -> i.getId().equals(customIngredientId))
                .findFirst()
                .orElseThrow(() -> new CustomIngredientNotFoundException(customIngredientId));

        // Actualizar campos
        seasoningServiceImpl.update(ingredient, updatedData);

        return sectionMapper.toDto(sectionRepository.save(section));
    }


    @Override
    public SectionResponseDto removeCustomIngredient(Long recipeId, Long customIngredientId) {
        Section section = sectionRepository.findById(recipeId)
                .orElseThrow(() -> new SectionNotFoundException(recipeId));

        Seasoning ingredient = section.getSeasonings().stream()
                .filter(i -> i.getId().equals(customIngredientId))
                .findFirst()
                .orElseThrow(() -> new CustomIngredientNotFoundException(customIngredientId));

        section.getSeasonings().remove(ingredient);

        return sectionMapper.toDto(sectionRepository.save(section));
    }


//Steps

    @Override
    public SectionResponseDto addStepToRecipe(Long sectionId, StepRequestDto stepRequestDto) {
        Section section = this.findEntityById(sectionId);

        section.getSteps().add(stepMapper.toEntity(stepRequestDto)); // Añadir el paso

        return sectionMapper.toDto(sectionRepository.save(section));
    }

    @Override
    public SectionResponseDto updateStepInRecipe(Long sectionId, Integer stepNumber, StepRequestDto stepRequestDto) {
        Section section = this.findEntityById(sectionId);

        Optional<Step> optionalStep = section.getSteps()
                .stream()
                .filter(step -> step.getStepNumber().equals(stepNumber))
                .findFirst();

        if (optionalStep.isPresent()) {
            stepMapper.updateStepFromDto(stepRequestDto, optionalStep.get());
        } else {
            throw new StepNotFoundException("Step not found");
        }

        return sectionMapper.toDto(sectionRepository.save(section));
    }

    @Override
    public SectionResponseDto removeStepFromRecipe(Long recipeId, String stepDescription) {
        Section section = sectionRepository.findById(recipeId)
                .orElseThrow(() -> new SectionNotFoundException(recipeId));

        if (section.getSteps().remove(stepDescription)) {
            section = sectionRepository.save(section);
            return sectionMapper.toDto(section);
        } else {
            throw new StepNotFoundException("Step not found in recipe");
        }
    }

}
