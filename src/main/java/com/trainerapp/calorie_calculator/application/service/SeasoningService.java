package com.trainerapp.calorie_calculator.application.service;

import com.trainerapp.calorie_calculator.domain.model.Seasoning;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.SeasoningEntity;
import com.trainerapp.calorie_calculator.web.dto.request.SeasoningRequestDto;

public interface SeasoningService {
    /**
     * Crea un nuevo condimento basado en los datos proporcionados
     * @param seasoningRequestDto Datos para crear el condimento
     * @return El condimento creado
     */
    Seasoning create(SeasoningRequestDto seasoningRequestDto);

    /**
     * Actualiza un condimento existente con nuevos datos
     * @param seasoning Condimento a actualizar
     * @param updateDto Nuevos datos para el condimento
     */
    void update(Seasoning seasoning, SeasoningRequestDto updateDto);
}
