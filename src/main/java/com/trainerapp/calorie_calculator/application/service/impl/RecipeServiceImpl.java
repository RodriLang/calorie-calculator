package com.trainerapp.calorie_calculator.application.service.impl;

import com.trainerapp.calorie_calculator.domain.model.Recipe;
import com.trainerapp.calorie_calculator.domain.model.Section;
import com.trainerapp.calorie_calculator.domain.model.Tag;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.RecipeEntity;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.SectionEntity;
import com.trainerapp.calorie_calculator.infrastructure.persistence.mapper.SectionEntityMapper;
import com.trainerapp.calorie_calculator.web.dto.request.RecipeRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.SectionRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.RecipeResponseDto;
import com.trainerapp.calorie_calculator.application.exception.RecipeNotFoundException;
import com.trainerapp.calorie_calculator.infrastructure.persistence.mapper.RecipeEntityMapper;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.TagEntity;
import com.trainerapp.calorie_calculator.domain.repository.RecipeRepository;
import com.trainerapp.calorie_calculator.application.service.RecipeService;
import com.trainerapp.calorie_calculator.web.mapper.RecipeDtoMapper;
import com.trainerapp.calorie_calculator.web.mapper.SectionDtoMapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public Recipe getModelById(Long id) {
        return recipeEntityMapper.toModel(recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id)));
    }

    @Override
    public RecipeResponseDto getRecipeById(Long id) {
        return recipeDtoMapper.toDto(getModelById(id));
    }

    @Override
    public RecipeResponseDto createRecipe(RecipeRequestDto recipeRequestDto) {

        Recipe recipe = recipeDtoMapper.toModel(recipeRequestDto);
        RecipeEntity recipeEntity = recipeEntityMapper.toEntity(recipe);

        return recipeDtoMapper.toDto(recipeEntityMapper.toModel(recipeEntity));
    }

    @Override
    public RecipeResponseDto updateRecipe(Long recipeId, RecipeRequestDto recipeRequestDto) {

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
    public void deleteRecipe(Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }

    @Override
    public RecipeResponseDto addSectionToRecipe(Long recipeId, SectionRequestDto sectionRequestDto) {
        Recipe recipe = this.getModelById(recipeId);
        Section section = sectionDtoMapper.toModel(sectionRequestDto);
        recipe.getSections().add(section);

        return recipeDtoMapper.toDto(saveRecipe(recipe));
    }

    @Override
    public RecipeResponseDto removeSectionFromRecipe(Long recipeId, Long sectionId) {

        //arreglar aca para que no reciba el id

        Recipe recipe = this.getModelById(recipeId);
        recipe.getSections().removeIf(s -> s.equals(sectionId));
        return recipeDtoMapper.toDto(saveRecipe(recipe));
    }

    @Override
    public RecipeResponseDto addTags(Long recipeId, List<TagRequestDto> tagsData) {
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
    public RecipeResponseDto removeTags(Long recipeId, List<Long> tagIds) {

        //arreglar la lista de iddds
        Recipe existingRecipe = this.getModelById(recipeId);
        existingRecipe.getTagList().removeIf(tag -> tagIds.contains(tag));
        return recipeDtoMapper.toDto(saveRecipe(existingRecipe));
    }

    private Recipe saveRecipe(Recipe recipe) {
        return recipeEntityMapper.toModel(recipeRepository.save(recipeEntityMapper.toEntity(recipe)));
    }
}
