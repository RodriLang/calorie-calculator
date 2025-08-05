package com.trainerapp.calorie_calculator.application.service.impl;

import com.trainerapp.calorie_calculator.application.service.IngredientService;
import com.trainerapp.calorie_calculator.application.service.SeasoningService;
import com.trainerapp.calorie_calculator.domain.model.Ingredient;
import com.trainerapp.calorie_calculator.domain.model.Seasoning;
import com.trainerapp.calorie_calculator.domain.model.Section;
import com.trainerapp.calorie_calculator.domain.model.Step;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.SectionEntity;
import com.trainerapp.calorie_calculator.web.dto.request.SeasoningRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.IngredientRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.SectionRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.StepRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.SectionResponseDto;
import com.trainerapp.calorie_calculator.application.exception.SeasoningNotFoundException;
import com.trainerapp.calorie_calculator.application.exception.IngredientNotFoundException;
import com.trainerapp.calorie_calculator.application.exception.SectionNotFoundException;
import com.trainerapp.calorie_calculator.application.exception.StepNotFoundException;
import com.trainerapp.calorie_calculator.infrastructure.persistence.mapper.SectionEntityMapper;
import com.trainerapp.calorie_calculator.domain.repository.SectionRepository;
import com.trainerapp.calorie_calculator.application.service.SectionService;
import com.trainerapp.calorie_calculator.web.mapper.SectionDtoMapper;
import com.trainerapp.calorie_calculator.web.mapper.StepDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Service
public class SectionServiceImpl implements SectionService {


    private final SectionRepository sectionRepository;
    private final SectionEntityMapper sectionEntityMapper;
    private final SectionDtoMapper sectionDtoMapper;
    private final StepDtoMapper stepDtoMapper;

    private final IngredientService ingredientService;
    private final SeasoningService seasoningService;


    @Override
    public List<SectionResponseDto> getSections() {
        return sectionRepository.findAll()
                .stream()
                .map(sectionEntityMapper::toModel)
                .map(sectionDtoMapper::toDto)
                .toList();
    }

    @Override
    public SectionResponseDto findById(UUID sectionId) {
        return sectionDtoMapper.toDto(findModelById(sectionId));
    }

    @Override
    public SectionEntity findEntityById(UUID sectionId) {
        return sectionRepository.findByPublicId(sectionId).orElseThrow(()
                -> new SectionNotFoundException(sectionId));
    }

    @Override
    public Section findModelById(UUID sectionId) {
        return sectionEntityMapper.toModel(findEntityById(sectionId));
    }


    @Override
    public SectionResponseDto createSection(SectionRequestDto sectionRequestDto) {


        Section section = sectionDtoMapper.toModel(sectionRequestDto);

        // Mapear ingredientes
        for (IngredientRequestDto ingredientDto : sectionRequestDto.ingredients()) {
            Ingredient ingredient = ingredientService.create(ingredientDto);
            section.getIngredients().add(ingredient);
        }

        // Mapear condimentos
        for (SeasoningRequestDto customDto : sectionRequestDto.seasonings()) {
            Seasoning seasoning = seasoningService.create(customDto);
            section.getSeasonings().add(seasoning);
        }

        // Guardar los pasos
        section.toBuilder()
                .steps(sectionRequestDto.steps()
                        .stream()
                        .map(stepDtoMapper::toModel)
                        .toList());

        return sectionDtoMapper.toDto(saveSection(section));
    }


    @Override
    public SectionResponseDto updateSection(UUID sectionId, SectionRequestDto sectionRequestDto) {

        Section section = findModelById(sectionId);

        sectionDtoMapper.updateFromDto(sectionRequestDto, section.toBuilder());

        // Limpiar y actualizar ingredientes
        section.getIngredients().clear();
        for (IngredientRequestDto ingredientDto : sectionRequestDto.ingredients()) {
            Ingredient ingredient = ingredientService.create(ingredientDto);
            section.getIngredients().add(ingredient);
        }

        // Limpiar y actualizar condimentos
        section.getSeasonings().clear();
        for (SeasoningRequestDto customDto : sectionRequestDto.seasonings()) {
            Seasoning seasoning = seasoningService.create(customDto);
            section.getSeasonings().add(seasoning);
        }

        // Actualizar pasos
        section.getSteps().clear();
        section.getSteps().addAll(sectionRequestDto.steps()
                .stream()
                .map(stepDtoMapper::toModel)
                .toList());


        return sectionDtoMapper.toDto(saveSection(section));
    }


    @Override
    public void deleteSection(UUID sectionId) {

        SectionEntity section = this.findEntityById(sectionId);
        sectionRepository.delete(section);
    }


    @Override
    public SectionResponseDto addIngredientToSection(UUID sectionId, IngredientRequestDto ingredientRequestDto) {
        Section section = findModelById(sectionId);

        Ingredient ingredient = ingredientService.create(ingredientRequestDto);

        section.getIngredients().add(ingredient);

        return sectionDtoMapper.toDto(saveSection(section));
    }

    @Override
    public SectionResponseDto removeIngredientFromSection(UUID sectionId, UUID ingredientId) {
        Section section = findModelById(sectionId);

        boolean removed = section
                .getIngredients()
                .removeIf(i -> i.getPublicId().equals(ingredientId));

        if (!removed) {
            throw new IngredientNotFoundException("Ingredient not found with id: " + ingredientId + " in recipe id: " + sectionId);
        }

        return sectionDtoMapper.toDto(saveSection(section));
    }


    @Override
    public SectionResponseDto updateIngredientInSection(UUID sectionId, UUID ingredientId, IngredientRequestDto newIngredientData) {
        Section section = findModelById(sectionId);


        Ingredient ingredient = section.getIngredients().stream()
                .filter(i -> i.getPublicId().equals(ingredientId))
                .findFirst()
                .orElseThrow(() -> new IngredientNotFoundException(ingredientId));

        // Actualizar usando IngredientService
        ingredientService.update(ingredient, newIngredientData);

        return sectionDtoMapper.toDto(saveSection(section));
    }


//Seasonings

    @Override
    public SectionResponseDto addSeasoningToSection(UUID recipeId, UUID sectionId, SeasoningRequestDto seasoningRequestDto) {
        Section section = findModelById(sectionId);


        section.getSeasonings().add(seasoningService.create(seasoningRequestDto));

        return sectionDtoMapper.toDto(saveSection(section));

    }

    @Override
    public SectionResponseDto updateSeasoning(UUID recipeId, UUID sectionId, SeasoningRequestDto updatedData) {
        Section section = findModelById(sectionId);


        Seasoning seasoning = section.getSeasonings().stream()
                .filter(i -> i.getPublicId().equals(sectionId))
                .findFirst()
                .orElseThrow(() -> new SeasoningNotFoundException(sectionId));

        seasoningService.update(seasoning, updatedData);

        return sectionDtoMapper.toDto(saveSection(section));
    }


    @Override
    public SectionResponseDto removeCustomIngredient(UUID sectionId, UUID customIngredientId) {
        Section section = findModelById(sectionId);


        Seasoning ingredient = section.getSeasonings().stream()
                .filter(i -> i.getPublicId().equals(customIngredientId))
                .findFirst()
                .orElseThrow(() -> new SeasoningNotFoundException(customIngredientId));

        section.getSeasonings().remove(ingredient);

        return sectionDtoMapper.toDto(saveSection(section));
    }


//Steps

    @Override
    public SectionResponseDto addStepToRecipe(UUID sectionId, StepRequestDto stepRequestDto) {
        Section section = findModelById(sectionId);

        section.getSteps().add(
                stepDtoMapper.toModel(stepRequestDto)); // Añadir el paso

        return sectionDtoMapper.toDto(saveSection(section));
    }

    @Override
    public SectionResponseDto updateStepInRecipe(UUID sectionId, Integer stepNumber, StepRequestDto stepRequestDto) {
        Section section = findModelById(sectionId);

        Optional<Step> optionalStep = section.getSteps()
                .stream()
                .filter(step -> step.getStepNumber().equals(stepNumber))
                .findFirst();

        if (optionalStep.isPresent()) {
            stepDtoMapper.updateFromDto(stepRequestDto, optionalStep.get().toBuilder());
        } else {
            throw new StepNotFoundException("Step not found");
        }

        return sectionDtoMapper.toDto(saveSection(section));
    }

    @Override
    public SectionResponseDto removeStepFromSection(UUID sectionId, Integer stepNumber) {

        Section section = findModelById(sectionId);

        Step step = section.getSteps().stream()
                .filter(s -> s.getStepNumber().equals(stepNumber))
                .findFirst()
                .orElseThrow(() -> new StepNotFoundException("Step not found"));


        if (section.getSteps().remove(step)) {
            return sectionDtoMapper.toDto(saveSection(section));

        } else {
            throw new StepNotFoundException("Step not found in recipe");
        }
    }

    private Section saveSection(Section section) {
        SectionEntity sectionEntity = sectionEntityMapper.toEntity(section);
        return sectionEntityMapper.toModel(sectionRepository.save(sectionEntity));
    }

}
