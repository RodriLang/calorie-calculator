package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.dto.filter.MealFilterParamsDto;
import com.trainerapp.calorie_calculator.dto.response.MealCardResponseDto;
import com.trainerapp.calorie_calculator.dto.response.RecipeResponseDto;
import com.trainerapp.calorie_calculator.dto.request.MealRequestDto;
import com.trainerapp.calorie_calculator.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.exception.MealNotFoundException;
import com.trainerapp.calorie_calculator.mapper.MealMapper;
import com.trainerapp.calorie_calculator.mapper.RecipeMapper;
import com.trainerapp.calorie_calculator.dto.response.MealResponseDto;
import com.trainerapp.calorie_calculator.model.entity.Meal;
import com.trainerapp.calorie_calculator.model.entity.Recipe;
import com.trainerapp.calorie_calculator.model.entity.Tag;
import com.trainerapp.calorie_calculator.repository.MealRepository;
import com.trainerapp.calorie_calculator.specification.MealSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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


    public List<Meal> getMeals() {
        return mealRepository.findAll();
    }

    public Meal getMealEntityById(Long id) {
        return mealRepository.findById(id).orElseThrow(()
                -> new MealNotFoundException(id));
    }

    public Page<MealCardResponseDto> filterMealCards(MealFilterParamsDto params, Pageable pageable) {
        Specification<Meal> spec = MealSpecifications.withFilters(params);
        return mealRepository.findAll(spec, pageable)
                .map(mealMapper::toCardDto);
    }


    public MealResponseDto getMealById(Long id) {
        return mealMapper.toDto(mealRepository.findById(id).orElseThrow(()
                -> new MealNotFoundException(id)));
    }

    public MealResponseDto saveRecipe(MealRequestDto mealRequestDto) {
        return mealMapper.toDto(mealRepository.save(mealMapper.fromDataDto(mealRequestDto)));
    }

    public MealResponseDto createMeal(MealRequestDto mealRequestDto) {
        Meal meal = Meal.builder()
                .name(mealRequestDto.name())
                .url(mealRequestDto.url())
                .recipeList(
                        Optional.ofNullable(mealRequestDto.recipes())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(recipeMapper::fromDto)
                                .toList())
                .shortDescription(mealRequestDto.shortDescription())
                .tagList(
                        Optional.ofNullable(mealRequestDto.tags())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(tagService::findOrCreateByDataDto)
                                .toList())
                .build();

        return mealMapper.toDto(mealRepository.save(meal));
    }

    public MealResponseDto updateMeal(Long mealId, MealRequestDto mealRequestDto) {
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new MealNotFoundException(mealId));

        meal.setName(mealRequestDto.name());
        meal.setUrl(mealRequestDto.url());
        meal.setShortDescription(mealRequestDto.shortDescription());

        // Actualizar recetas asociadas
        List<Recipe> recipes = Optional.ofNullable(mealRequestDto.recipes())
                .orElse(Collections.emptyList())
                .stream()
                .map(recipeMapper::fromDto)
                .toList();
        meal.setRecipeList(recipes);

        // Actualizar tags asociados
        List<Tag> tags = Optional.ofNullable(mealRequestDto.tags())
                .orElse(Collections.emptyList())
                .stream()
                .map(tagService::findOrCreateByDataDto)
                .toList();
        meal.setTagList(tags);

        return mealMapper.toDto(mealRepository.save(meal));
    }

    public void removeMeal(Long mealId) {
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new MealNotFoundException("Meal not found with id: " + mealId));
        mealRepository.delete(meal);
    }

    public List<MealCardResponseDto> filterMealCardsByTags(List<Tag> tags) {
        return mealRepository.findByTagListIn(tags)
                .stream()
                .map(mealMapper::toCardDto)
                .toList();
    }

    public List<MealCardResponseDto> getAllMealCards() {
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
    public MealResponseDto addRecipeToMeal(Long mealId, RecipeResponseDto recipeResponseDto) {
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new MealNotFoundException(mealId));
        return mealMapper.toDto(meal);
    }

    public MealResponseDto removeRecipeFromMeal(Long mealId, Long recipeId) {
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new MealNotFoundException("Meal not found with id: " + mealId));

        meal.getRecipeList().removeIf(recipe -> recipe.getId().equals(recipeId));
        return mealMapper.toDto(mealRepository.save(meal));
    }

    public MealResponseDto addTags(Long mealId, List<TagRequestDto> tagsData) {
        Meal existingMeal = mealRepository.findById(mealId)
                .orElseThrow(() -> new MealNotFoundException(mealId));

        // Verifica si los tags existen, si no los crea
        List<Tag> tagsToAdd = tagsData.stream()
                .map(tagService::findOrCreateByDataDto)
                .toList();

        // Añade los tags evitando duplicados
        tagsToAdd.forEach(tag -> {
            if (!existingMeal.getTagList().contains(tag)) {
                existingMeal.getTagList().add(tag);
            }
        });

        return mealMapper.toDto(mealRepository.save(existingMeal));
    }

    public MealResponseDto removeTags(Long mealId, List<Long> tagIds) {
        Meal existingFood = mealRepository.findById(mealId)
                .orElseThrow(() -> new MealNotFoundException(mealId));

        existingFood.getTagList().removeIf(tag -> tagIds.contains(tag.getId()));

        return mealMapper.toDto(mealRepository.save(existingFood));
    }

    //Métodos con paginación
    public Page<MealCardResponseDto> getAllMealCards(Pageable pageable) {
        return mealRepository.findAll(pageable)
                .map(mealMapper::toCardDto);
    }

    public Page<MealCardResponseDto> filterMealCardsByTags(List<Tag> tags, Pageable pageable) {
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
