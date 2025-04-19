package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.exception.RecipeNotFoundException;
import com.trainerapp.calorie_calculator.mapper.RecipeMapper;
import com.trainerapp.calorie_calculator.mapper.RecipeSectionMapper;
import com.trainerapp.calorie_calculator.model.dto.RecipeCardDto;
import com.trainerapp.calorie_calculator.model.dto.RecipeDto;
import com.trainerapp.calorie_calculator.model.dto.RecipeSectionDto;
import com.trainerapp.calorie_calculator.model.dto.create.RecipeDataDto;
import com.trainerapp.calorie_calculator.model.dto.create.TagDataDto;
import com.trainerapp.calorie_calculator.model.entity.Recipe;
import com.trainerapp.calorie_calculator.model.entity.RecipeSection;
import com.trainerapp.calorie_calculator.model.entity.Tag;
import com.trainerapp.calorie_calculator.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    private final RecipeSectionMapper recipeSectionMapper;
    private final TagService tagService;


    public List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeEntityById(Long id) {
        return recipeRepository.findById(id).orElseThrow(()
                -> new RecipeNotFoundException(id));
    }

    public RecipeDto getRecipeById(Long id) {
        return recipeMapper.toDto(recipeRepository.findById(id).orElseThrow(()
                -> new RecipeNotFoundException(id)));
    }

    public RecipeDto saveRecipe(RecipeDataDto recipeDataDto) {
        return recipeMapper.toDto(recipeRepository.save(recipeMapper.fromDataDto(recipeDataDto)));
    }

    public RecipeDto createRecipe(RecipeDataDto recipeDataDto) {
        Recipe recipe = Recipe.builder()
                .name(recipeDataDto.name())
                .url(recipeDataDto.url())
                .sections(
                        Optional.ofNullable(recipeDataDto.recipes())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(recipeSectionMapper::fromDto)
                                .toList())
                .description(recipeDataDto.shortDescription())
                .tags(
                        Optional.ofNullable(recipeDataDto.tags())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(tagService::findOrCreateByDataDto)
                                .toList())
                .build();

        return recipeMapper.toDto(recipeRepository.save(recipe));
    }

    public RecipeDto updateRecipes(Long mealId, RecipeDataDto recipeDataDto) {
        Recipe recipe = recipeRepository.findById(mealId)
                .orElseThrow(() -> new RecipeNotFoundException(mealId));

        recipe.setName(recipeDataDto.name());
        recipe.setUrl(recipeDataDto.url());
        recipe.setDescription(recipeDataDto.shortDescription());

        // Actualizar recetas asociadas
        List<RecipeSection> recipeSections = Optional.ofNullable(recipeDataDto.recipes())
                .orElse(Collections.emptyList())
                .stream()
                .map(recipeSectionMapper::fromDto)
                .toList();
        recipe.setSections(recipeSections);

        // Actualizar tags asociados
        List<Tag> tags = Optional.ofNullable(recipeDataDto.tags())
                .orElse(Collections.emptyList())
                .stream()
                .map(tagService::findOrCreateByDataDto)
                .toList();
        recipe.setTags(tags);

        return recipeMapper.toDto(recipeRepository.save(recipe));
    }

    public void removeRecipes(Long mealId) {
        Recipe recipe = recipeRepository.findById(mealId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + mealId));
        recipeRepository.delete(recipe);
    }

    public List<RecipeCardDto> filterRecipeCardsByTags(List<Tag> tags) {
        return recipeRepository.findByTags(tags)
                .stream()
                .map(recipeMapper::toCardDto)
                .toList();
    }

    public List<RecipeCardDto> getAllRecipeCards() {
        return recipeRepository.findAll()
                .stream()
                .map(recipeMapper::toCardDto)
                .toList();
    }
/*
    private RecipeCardDto toMealCardDto(Recipe meal) {

        String difficulty = meal.getRecipeSectionList().stream()
                .map(recipe -> recipe.getDifficulty().name())
                .max(Comparator.naturalOrder())
                .orElse("UNKNOWN");

        Duration totalTime = meal.getRecipeSectionList().stream()
                .map(RecipeSection::getPreparationTime)
                .reduce(Duration.ZERO, Duration::plus);

        String preparationTime = formatDuration(totalTime);

        return new RecipeCardDto(
                meal.getId(),
                meal.getName(),
                meal.getUrl(),
                difficulty,
                preparationTime
        );
    }
*/
    public RecipeDto addRecipeToMeal(Long mealId, RecipeSectionDto recipeSectionDto) {
        Recipe recipe = recipeRepository.findById(mealId)
                .orElseThrow(() -> new RecipeNotFoundException(mealId));
        return recipeMapper.toDto(recipe);
    }

    public RecipeDto removeRecipeFromMeal(Long mealId, Long recipeId) {
        Recipe meal = recipeRepository.findById(mealId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + mealId));

        meal.getSections().removeIf(recipe -> recipe.getId().equals(recipeId));
        return recipeMapper.toDto(recipeRepository.save(meal));
    }

    public RecipeDto addTags(Long mealId, List<TagDataDto> tagsData) {
        Recipe existingRecipe = recipeRepository.findById(mealId)
                .orElseThrow(() -> new RecipeNotFoundException(mealId));

        // Verifica si los tags existen, si no los crea
        List<Tag> tagsToAdd = tagsData.stream()
                .map(tagService::findOrCreateByDataDto)
                .toList();

        // Añade los tags evitando duplicados
        tagsToAdd.forEach(tag -> {
            if (!existingRecipe.getTags().contains(tag)) {
                existingRecipe.getTags().add(tag);
            }
        });

        return recipeMapper.toDto(recipeRepository.save(existingRecipe));
    }

    public RecipeDto removeTags(Long mealId, List<Long> tagIds) {
        Recipe existingFood = recipeRepository.findById(mealId)
                .orElseThrow(() -> new RecipeNotFoundException(mealId));

        existingFood.getTags().removeIf(tag -> tagIds.contains(tag.getId()));

        return recipeMapper.toDto(recipeRepository.save(existingFood));
    }

    //Métodos con paginación
    public Page<RecipeCardDto> getAllMealCards(Pageable pageable) {
        return recipeRepository.findAll(pageable)
                .map(recipeMapper::toCardDto);
    }

    public Page<RecipeCardDto> filterMealCardsByTags(List<Tag> tags, Pageable pageable) {
        return recipeRepository.findByTags(tags, pageable)
                .map(recipeMapper::toCardDto);
    }

    //Métodos privados
    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        return String.format("%02d:%02d", hours, minutes);
    }

}
