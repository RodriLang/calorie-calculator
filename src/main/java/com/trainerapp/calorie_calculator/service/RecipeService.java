package com.trainerapp.calorie_calculator.service;

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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    private final TagService tagService;
    private final SectionMapper sectionMapper;


    public List<RecipeResponseDto> getRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(recipeMapper::toDto)
                .toList();
    }

    public Recipe getRecipeEntityById(Long id) {
        return recipeRepository.findById(id).orElseThrow(()
                -> new RecipeNotFoundException(id));
    }
/*
    public Page<RecipeCardResponseDto> filterRecipes(MealFilterParamsDto params, Pageable pageable) {
        Specification<Recipe> spec = MealSpecifications.withFilters(params);
        return recipeRepository.findAll(spec, pageable)
                .map(recipeMapper::toDto);
    }
*/

    public RecipeResponseDto getRecipeById(Long id) {
        return recipeMapper.toDto(this.getRecipeEntityById(id));
    }

    public RecipeResponseDto createRecipe(RecipeRequestDto recipeRequestDto) {
        return recipeMapper.toDto(recipeRepository.save(recipeMapper.toEntity(recipeRequestDto)));
    }

    public RecipeResponseDto updateRecipe(Long recipeId, RecipeRequestDto recipeRequestDto) {
        Recipe recipe = this.getRecipeEntityById(recipeId);
        recipeMapper.updateRecipeFromDto(recipeRequestDto, recipe);

        // Actualizar recetas asociadas
        List<Section> sections = Optional.ofNullable(recipeRequestDto.sections())
                .orElse(Collections.emptyList())
                .stream()
                .map(sectionMapper::toEntity)
                .toList();
        recipe.setSections(sections);

        // Actualizar tags asociados
        List<Tag> tags = Optional.ofNullable(recipeRequestDto.tags())
                .orElse(Collections.emptyList())
                .stream()
                .map(tagService::findOrCreateByDataDto)
                .toList();
        recipe.setTagList(tags);

        return recipeMapper.toDto(recipeRepository.save(recipe));
    }

    public void deleteRecipe(Long recipeId) {
        Recipe recipe = this.getRecipeEntityById(recipeId);
        recipeRepository.delete(recipe);
    }


    public RecipeResponseDto addSectionToRecipe(Long recipeId, SectionRequestDto sectionRequestDto) {

        Recipe recipe = this.getRecipeEntityById(recipeId);

        Section section = sectionMapper.toEntity(sectionRequestDto);

        recipe.getSections().add(section);

        return recipeMapper.toDto(recipeRepository.save(recipe));
    }


    public RecipeResponseDto removeSectionFromRecipe(Long recipeId, Long sectionId) {
        Recipe recipe = this.getRecipeEntityById(recipeId);

        recipe.getSections().removeIf(s -> s.getId().equals(sectionId));
        return recipeMapper.toDto(recipeRepository.save(recipe));
    }

    public RecipeResponseDto addTags(Long recipeId, List<TagRequestDto> tagsData) {
        Recipe existingRecipe = this.getRecipeEntityById(recipeId);

        // Verifica si los tags existen, si no los crea
        List<Tag> tagsToAdd = tagsData.stream()
                .map(tagService::findOrCreateByDataDto)
                .toList();

        // Añade los tags evitando duplicados
        tagsToAdd.forEach(tag -> {
            if (!existingRecipe.getTagList().contains(tag)) {
                existingRecipe.getTagList().add(tag);
            }
        });

        return recipeMapper.toDto(recipeRepository.save(existingRecipe));
    }

    public RecipeResponseDto removeTags(Long recipeId, List<Long> tagIds) {
        Recipe existingFood = this.getRecipeEntityById(recipeId);

        existingFood.getTagList().removeIf(tag -> tagIds.contains(tag.getId()));

        return recipeMapper.toDto(recipeRepository.save(existingFood));
    }

  /*  //Métodos con paginación
    public Page<RecipeResponseDto> getAllMealCards(Pageable pageable) {
        return recipeRepository.findAll(pageable)
                .map(recipeMapper::toDto);
    }

    public Page<RecipeCardResponseDto> filterMealCardsByTags(List<Tag> tags, Pageable pageable) {
        return recipeRepository.findByTagListIn(tags, pageable)
                .map(mealMapper::toCardDto);
    }
    */
   /* //Métodos privados
    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
     */
}
