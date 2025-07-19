package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.dto.request.SeasoningRequestDto;
import com.trainerapp.calorie_calculator.model.entity.Seasoning;

public interface SeasoningService {
    /**
     * Crea un nuevo condimento basado en los datos proporcionados
     * @param seasoningRequestDto Datos para crear el condimento
     * @return El condimento creado
     */
    Seasoning create(SeasoningRequestDto seasoningRequestDto);

    /**
     * Actualiza un condimento existente con nuevos datos
     * @param ingredient Condimento a actualizar
     * @param data Nuevos datos para el condimento
     */
    void update(Seasoning ingredient, SeasoningRequestDto data);
}
