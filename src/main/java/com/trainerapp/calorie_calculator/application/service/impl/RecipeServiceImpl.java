package com.trainerapp.calorie_calculator.application.service.impl;

import com.trainerapp.calorie_calculator.domain.model.Recipe;
import com.trainerapp.calorie_calculator.domain.model.Section;
import com.trainerapp.calorie_calculator.domain.model.Tag;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.RecipeEntity;
import com.trainerapp.calorie_calculator.infrastructure.persistence.mapper.SectionEntityMapper;
import com.trainerapp.calorie_calculator.web.dto.request.RecipeRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.SectionRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.RecipeResponseDto;
import com.trainerapp.calorie_calculator.application.exception.RecipeNotFoundException;
import com.trainerapp.calorie_calculator.infrastructure.persistence.mapper.RecipeEntityMapper;
import com.trainerapp.calorie_calculator.domain.repository.RecipeRepository;
import com.trainerapp.calorie_calculator.application.service.RecipeService;
import com.trainerapp.calorie_calculator.web.mapper.RecipeDtoMapper;
import com.trainerapp.calorie_calculator.web.mapper.SectionDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeDtoMapper recipeDtoMapper;
    private final RecipeEntityMapper recipeEntityMapper;
    private final TagServiceImpl tagServiceImpl;
    private final SectionEntityMapper sectionEntityMapper;
    private final SectionDtoMapper sectionDtoMapper;

    @Override
    public List<RecipeResponseDto> getAllRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(recipeEntityMapper::toModel)
                .map(recipeDtoMapper::toDto)
                .toList();
    }

    @Override
    public Recipe getModelById(UUID id) {
        return recipeEntityMapper.toModel(this.findEntity(id));
    }

    @Override
    public RecipeResponseDto getRecipeById(UUID id) {
        return recipeDtoMapper.toDto(getModelById(id));
    }

    @Override
    public RecipeResponseDto createRecipe(RecipeRequestDto recipeRequestDto) {

        Recipe recipe = recipeDtoMapper.toModel(recipeRequestDto);
        RecipeEntity recipeEntity = recipeEntityMapper.toEntity(recipe);

        return recipeDtoMapper.toDto(recipeEntityMapper.toModel(recipeEntity));
    }

    @Override
    public RecipeResponseDto updateRecipe(UUID recipeId, RecipeRequestDto recipeRequestDto) {

        Recipe recipe = this.getModelById(recipeId);

        Recipe updatedRecipe = recipeDtoMapper.updateRecipeFromDto(recipeRequestDto, recipe);
        Recipe.RecipeBuilder builder = updatedRecipe.toBuilder();
        List<Section> sections = Optional.ofNullable(recipeRequestDto.sections())
                .orElse(Collections.emptyList())
                .stream()
                .map(sectionDtoMapper::toModel)
                .toList();
        builder.sections(sections);

        List<Tag> tags = Optional.ofNullable(recipeRequestDto.tags())
                .orElse(Collections.emptyList())
                .stream()
                .map(tagServiceImpl::findOrCreateByDataDto)
                .toList();

        builder.tagList(tags);

        builder.build();
        RecipeEntity recipeEntity = recipeEntityMapper.toEntity(recipe);

        return recipeDtoMapper.toDto(recipeEntityMapper.toModel(recipeRepository.save(recipeEntity)));
    }

    @Override
    public void deleteRecipe(UUID recipeId) {

        RecipeEntity recipeEntity = this.findEntity(recipeId);
        recipeRepository.delete(recipeEntity);
    }

    @Override
    public RecipeResponseDto addSectionToRecipe(UUID recipeId, SectionRequestDto sectionRequestDto) {
        Recipe recipe = this.getModelById(recipeId);
        Section section = sectionDtoMapper.toModel(sectionRequestDto);
        recipe.getSections().add(section);

        return recipeDtoMapper.toDto(saveRecipe(recipe));
    }

    @Override
    public RecipeResponseDto removeSectionFromRecipe(UUID recipeId, UUID sectionId) {

        Recipe recipe = this.getModelById(recipeId);
        recipe.getSections().removeIf(s -> s.getPublicId().equals(sectionId));
        return recipeDtoMapper.toDto(saveRecipe(recipe));
    }

    @Override
    public RecipeResponseDto addTags(UUID recipeId, List<TagRequestDto> tagsData) {
        Recipe existingRecipe = this.getModelById(recipeId);
        List<Tag> tagsToAdd = tagsData.stream()
                .map(tagServiceImpl::findOrCreateByDataDto)
                .toList();

        tagsToAdd.forEach(tag -> {
            if (!existingRecipe.getTagList().contains(tag)) {
                existingRecipe.getTagList().add(tag);
            }
        });

        return recipeDtoMapper.toDto(saveRecipe(existingRecipe));
    }

    @Override
    public RecipeResponseDto removeTags(UUID recipeId, List<UUID> tagIds) {

        Recipe existingRecipe = this.getModelById(recipeId);
        existingRecipe.getTagList().removeIf(tag -> tagIds.contains(tag.getPublicId()));
        return recipeDtoMapper.toDto(saveRecipe(existingRecipe));
    }

    private Recipe saveRecipe(Recipe recipe) {
        return recipeEntityMapper.toModel(recipeRepository.save(recipeEntityMapper.toEntity(recipe)));
    }

    private RecipeEntity findEntity(UUID id) {
        return recipeRepository.findByPublicId(id)
                .orElseThrow(() -> new RecipeNotFoundException(id));

    }
}
