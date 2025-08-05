package com.trainerapp.calorie_calculator.application.service.impl;

import com.trainerapp.calorie_calculator.application.service.IngredientService;
import com.trainerapp.calorie_calculator.application.service.SeasoningService;
import com.trainerapp.calorie_calculator.domain.model.Ingredient;
import com.trainerapp.calorie_calculator.domain.model.Seasoning;
import com.trainerapp.calorie_calculator.domain.model.Section;
import com.trainerapp.calorie_calculator.domain.model.Step;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.SectionEntity;
import com.trainerapp.calorie_calculator.infrastructure.persistence.mapper.StepEntityMapper;
import com.trainerapp.calorie_calculator.web.dto.request.SeasoningRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.IngredientRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.SectionRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.StepRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.SectionResponseDto;
import com.trainerapp.calorie_calculator.application.exception.CustomIngredientNotFoundException;
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

@Transactional
@RequiredArgsConstructor
@Service
public class SectionServiceImpl implements SectionService {


    private final SectionRepository sectionRepository;
    private final SectionEntityMapper sectionEntityMapper;
    private final SectionDtoMapper sectionDtoMapper;
    private final StepEntityMapper stepEntityMapper;
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
    public SectionResponseDto findById(Long sectionId) {
        return sectionDtoMapper.toDto(findModelById(sectionId));
    }

    @Override
    public SectionEntity findEntityById(Long sectionId) {
        return sectionRepository.findById(sectionId).orElseThrow(()
                -> new SectionNotFoundException(sectionId));
    }

    @Override
    public Section findModelById(Long sectionId) {
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
    public SectionResponseDto updateSection(Long sectionId, SectionRequestDto sectionRequestDto) {

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
    public void deleteSection(Long sectionId) {
        SectionEntity section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new SectionNotFoundException(sectionId));
        sectionRepository.delete(section);
    }


    @Override
    public SectionResponseDto addIngredientToSection(Long sectionId, IngredientRequestDto ingredientRequestDto) {
        Section section = findModelById(sectionId);

        Ingredient ingredient = ingredientService.create(ingredientRequestDto);

        section.getIngredients().add(ingredient);

        return sectionDtoMapper.toDto(saveSection(section));
    }

    @Override
    public SectionResponseDto removeIngredientFromSection(Long sectionId, Long ingredientId) {
        Section section = findModelById(sectionId);

        //arreglar esto para que no reciba el idddd
        boolean removed = section.getIngredients().removeIf(i -> i.equals(ingredientId));

        if (!removed) {
            throw new IngredientNotFoundException("Ingredient not found with id: " + ingredientId + " in recipe id: " + sectionId);
        }

        return sectionDtoMapper.toDto(saveSection(section));
    }


    @Override
    public SectionResponseDto updateIngredientInSection(Long sectionId, Long ingredientId, IngredientRequestDto newIngredientData) {
        Section section = findModelById(sectionId);


        Ingredient ingredient = section.getIngredients().stream()
                .filter(i -> i.equals(ingredientId))
                .findFirst()
                .orElseThrow(() -> new IngredientNotFoundException(ingredientId));

        // Actualizar usando IngredientService
        ingredientService.update(ingredient, newIngredientData);

        return sectionDtoMapper.toDto(saveSection(section));
    }




//Seasonings

    @Override
    public SectionResponseDto addSeasoningToSection(Long recipeId, Long sectionId, SeasoningRequestDto seasoningRequestDto) {
        Section section = findModelById(sectionId);


        section.getSeasonings().add(seasoningService.create(seasoningRequestDto));

        return sectionDtoMapper.toDto(saveSection(section));

    }

    @Override
    public SectionResponseDto updateSeasoning(Long recipeId, Long sectionId, SeasoningRequestDto updatedData) {
        Section section = findModelById(sectionId);


        Seasoning seasoning = section.getSeasonings().stream()
                .filter(i -> i.equals(sectionId))
                .findFirst()
                .orElseThrow(() -> new CustomIngredientNotFoundException(sectionId));

        // Actualizar campos
        seasoningService.update(seasoning, updatedData);

        return sectionDtoMapper.toDto(saveSection(section));
    }


    @Override
    public SectionResponseDto removeCustomIngredient(Long sectionId, Long customIngredientId) {
        Section section = findModelById(sectionId);


        Seasoning ingredient = section.getSeasonings().stream()
                .filter(i -> i.equals(customIngredientId))
                .findFirst()
                .orElseThrow(() -> new CustomIngredientNotFoundException(customIngredientId));

        section.getSeasonings().remove(ingredient);

        return sectionDtoMapper.toDto(saveSection(section));
    }


//Steps

    @Override
    public SectionResponseDto addStepToRecipe(Long sectionId, StepRequestDto stepRequestDto) {
        Section section = findModelById(sectionId);

        section.getSteps().add(
                stepDtoMapper.toModel(stepRequestDto)); // Añadir el paso

        return sectionDtoMapper.toDto(saveSection(section));
    }

    @Override
    public SectionResponseDto updateStepInRecipe(Long sectionId, Integer stepNumber, StepRequestDto stepRequestDto) {
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
    public SectionResponseDto removeStepFromSection(Long sectionId, String stepDescription) {
        Section section = findModelById(sectionId);


        if (section.getSteps().remove(stepDescription)) {
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
