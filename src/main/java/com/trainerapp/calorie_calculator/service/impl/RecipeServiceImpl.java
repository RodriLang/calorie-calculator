package com.trainerapp.calorie_calculator.service.impl;

import com.trainerapp.calorie_calculator.dto.request.RecipeRequestDto;
import com.trainerapp.calorie_calculator.dto.request.SectionRequestDto;
import com.trainerapp.calorie_calculator.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.dto.response.RecipeResponseDto;
import com.trainerapp.calorie_calculator.exception.RecipeNotFoundException;
import com.trainerapp.calorie_calculator.mapper.RecipeMapper;
import com.trainerapp.calorie_calculator.mapper.SectionMapper;
import com.trainerapp.calorie_calculator.model.entity.Recipe;
import com.trainerapp.calorie_calculator.model.entity.Section;
import com.trainerapp.calorie_calculator.model.entity.Tag;
import com.trainerapp.calorie_calculator.repository.RecipeRepository;
import com.trainerapp.calorie_calculator.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    private final TagServiceImpl tagServiceImpl;
    private final SectionMapper sectionMapper;

    @Override
    public List<RecipeResponseDto> getAllRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(recipeMapper::toDto)
                .toList();
    }

    @Override
    public Recipe getRecipeEntityById(Long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException(id));
    }

    @Override
    public RecipeResponseDto getRecipeById(Long id) {
        return recipeMapper.toDto(this.getRecipeEntityById(id));
    }

    @Override
    public RecipeResponseDto createRecipe(RecipeRequestDto recipeRequestDto) {
        return recipeMapper.toDto(recipeRepository.save(recipeMapper.toEntity(recipeRequestDto)));
    }

    @Override
    public RecipeResponseDto updateRecipe(Long recipeId, RecipeRequestDto recipeRequestDto) {
        Recipe recipe = this.getRecipeEntityById(recipeId);
        recipeMapper.updateRecipeFromDto(recipeRequestDto, recipe);

        List<Section> sections = Optional.ofNullable(recipeRequestDto.sections())
                .orElse(Collections.emptyList())
                .stream()
                .map(sectionMapper::toEntity)
                .toList();
        recipe.setSections(sections);

        List<Tag> tags = Optional.ofNullable(recipeRequestDto.tags())
                .orElse(Collections.emptyList())
                .stream()
                .map(tagServiceImpl::findOrCreateByDataDto)
                .toList();
        recipe.setTagList(tags);

        return recipeMapper.toDto(recipeRepository.save(recipe));
    }

    @Override
    public void deleteRecipe(Long recipeId) {
        Recipe recipe = this.getRecipeEntityById(recipeId);
        recipeRepository.delete(recipe);
    }

    @Override
    public RecipeResponseDto addSectionToRecipe(Long recipeId, SectionRequestDto sectionRequestDto) {
        Recipe recipe = this.getRecipeEntityById(recipeId);
        Section section = sectionMapper.toEntity(sectionRequestDto);
        recipe.getSections().add(section);
        return recipeMapper.toDto(recipeRepository.save(recipe));
    }

    @Override
    public RecipeResponseDto removeSectionFromRecipe(Long recipeId, Long sectionId) {
        Recipe recipe = this.getRecipeEntityById(recipeId);
        recipe.getSections().removeIf(s -> s.getId().equals(sectionId));
        return recipeMapper.toDto(recipeRepository.save(recipe));
    }

    @Override
    public RecipeResponseDto addTags(Long recipeId, List<TagRequestDto> tagsData) {
        Recipe existingRecipe = this.getRecipeEntityById(recipeId);
        List<Tag> tagsToAdd = tagsData.stream()
                .map(tagServiceImpl::findOrCreateByDataDto)
                .toList();

        tagsToAdd.forEach(tag -> {
            if (!existingRecipe.getTagList().contains(tag)) {
                existingRecipe.getTagList().add(tag);
            }
        });

        return recipeMapper.toDto(recipeRepository.save(existingRecipe));
    }

    @Override
    public RecipeResponseDto removeTags(Long recipeId, List<Long> tagIds) {
        Recipe existingRecipe = this.getRecipeEntityById(recipeId);
        existingRecipe.getTagList().removeIf(tag -> tagIds.contains(tag.getId()));
        return recipeMapper.toDto(recipeRepository.save(existingRecipe));
    }
}
