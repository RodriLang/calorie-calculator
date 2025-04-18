package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.exception.MealNotFoundException;
import com.trainerapp.calorie_calculator.mapper.MealMapper;
import com.trainerapp.calorie_calculator.mapper.RecipeMapper;
import com.trainerapp.calorie_calculator.model.dto.MealCardDto;
import com.trainerapp.calorie_calculator.model.dto.MealDto;
import com.trainerapp.calorie_calculator.model.dto.RecipeDto;
import com.trainerapp.calorie_calculator.model.dto.create.MealDataDto;
import com.trainerapp.calorie_calculator.model.dto.create.TagDataDto;
import com.trainerapp.calorie_calculator.model.entity.Recipe;
import com.trainerapp.calorie_calculator.model.entity.RecipeSection;
import com.trainerapp.calorie_calculator.model.entity.Tag;
import com.trainerapp.calorie_calculator.repository.MealRepository;
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
public class MealService {

    private final MealRepository mealRepository;
    private final MealMapper mealMapper;
    private final RecipeMapper recipeMapper;
    private final TagService tagService;


    public List<Recipe> getMeals() {
        return mealRepository.findAll();
    }

    public Recipe getMealEntityById(Long id) {
        return mealRepository.findById(id).orElseThrow(()
                -> new MealNotFoundException(id));
    }

    public MealDto getMealById(Long id) {
        return mealMapper.toDto(mealRepository.findById(id).orElseThrow(()
                -> new MealNotFoundException(id)));
    }

    public MealDto saveRecipe(MealDataDto mealDataDto) {
        return mealMapper.toDto(mealRepository.save(mealMapper.fromDataDto(mealDataDto)));
    }

    public MealDto createMeal(MealDataDto mealDataDto) {
        Recipe recipe = Recipe.builder()
                .name(mealDataDto.name())
                .url(mealDataDto.url())
                .sections(
                        Optional.ofNullable(mealDataDto.recipes())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(recipeMapper::fromDto)
                                .toList())
                .description(mealDataDto.shortDescription())
                .tags(
                        Optional.ofNullable(mealDataDto.tags())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(tagService::findOrCreateByDataDto)
                                .toList())
                .build();

        return mealMapper.toDto(mealRepository.save(recipe));
    }

    public MealDto updateMeal(Long mealId, MealDataDto mealDataDto) {
        Recipe recipe = mealRepository.findById(mealId)
                .orElseThrow(() -> new MealNotFoundException(mealId));

        recipe.setName(mealDataDto.name());
        recipe.setUrl(mealDataDto.url());
        recipe.setDescription(mealDataDto.shortDescription());

        // Actualizar recetas asociadas
        List<RecipeSection> recipeSections = Optional.ofNullable(mealDataDto.recipes())
                .orElse(Collections.emptyList())
                .stream()
                .map(recipeMapper::fromDto)
                .toList();
        recipe.setRecipeSectionList(recipeSections);

        // Actualizar tags asociados
        List<Tag> tags = Optional.ofNullable(mealDataDto.tags())
                .orElse(Collections.emptyList())
                .stream()
                .map(tagService::findOrCreateByDataDto)
                .toList();
        recipe.setTags(tags);

        return mealMapper.toDto(mealRepository.save(recipe));
    }

    public void removeMeal(Long mealId) {
        Recipe recipe = mealRepository.findById(mealId)
                .orElseThrow(() -> new MealNotFoundException("Meal not found with id: " + mealId));
        mealRepository.delete(recipe);
    }

    public List<MealCardDto> filterMealCardsByTags(List<Tag> tags) {
        return mealRepository.findByTagListIn(tags)
                .stream()
                .map(mealMapper::toCardDto)
                .toList();
    }

    public List<MealCardDto> getAllMealCards() {
        return mealRepository.findAll()
                .stream()
                .map(mealMapper::toCardDto)
                .toList();
    }
/*
    private MealCardDto toMealCardDto(Meal meal) {

        String difficulty = meal.getRecipeList().stream()
                .map(recipe -> recipe.getDifficulty().name())
                .max(Comparator.naturalOrder())
                .orElse("UNKNOWN");

        Duration totalTime = meal.getRecipeList().stream()
                .map(Recipe::getPreparationTime)
                .reduce(Duration.ZERO, Duration::plus);

        String preparationTime = formatDuration(totalTime);

        return new MealCardDto(
                meal.getId(),
                meal.getName(),
                meal.getUrl(),
                difficulty,
                preparationTime
        );
    }
*/
    public MealDto addRecipeToMeal(Long mealId, RecipeDto recipeDto) {
        Recipe recipe = mealRepository.findById(mealId)
                .orElseThrow(() -> new MealNotFoundException(mealId));
        return mealMapper.toDto(recipe);
    }

    public MealDto removeRecipeFromMeal(Long mealId, Long recipeId) {
        Recipe meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new MealNotFoundException("Meal not found with id: " + mealId));

        meal.getRecipeSectionList().removeIf(recipe -> recipe.getId().equals(recipeId));
        return mealMapper.toDto(mealRepository.save(meal));
    }

    public MealDto addTags(Long mealId, List<TagDataDto> tagsData) {
        Recipe existingRecipe = mealRepository.findById(mealId)
                .orElseThrow(() -> new MealNotFoundException(mealId));

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

        return mealMapper.toDto(mealRepository.save(existingRecipe));
    }

    public MealDto removeTags(Long mealId, List<Long> tagIds) {
        Recipe existingFood = mealRepository.findById(mealId)
                .orElseThrow(() -> new MealNotFoundException(mealId));

        existingFood.getTags().removeIf(tag -> tagIds.contains(tag.getId()));

        return mealMapper.toDto(mealRepository.save(existingFood));
    }

    //Métodos con paginación
    public Page<MealCardDto> getAllMealCards(Pageable pageable) {
        return mealRepository.findAll(pageable)
                .map(mealMapper::toCardDto);
    }

    public Page<MealCardDto> filterMealCardsByTags(List<Tag> tags, Pageable pageable) {
        return mealRepository.findByTagListIn(tags, pageable)
                .map(mealMapper::toCardDto);
    }

    //Métodos privados
    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        return String.format("%02d:%02d", hours, minutes);
    }

}
