package com.trainerapp.calorie_calculator.web.mapper;

import com.trainerapp.calorie_calculator.domain.enums.UnitType;
import com.trainerapp.calorie_calculator.domain.model.Seasoning;
import com.trainerapp.calorie_calculator.web.dto.request.SeasoningRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.SeasoningResponseDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SeasoningDtoMapper {

    @Mapping(target = "unit", source = "unit", qualifiedByName = "toAbbreviation")
    SeasoningResponseDto toDto(Seasoning model);

    Seasoning toModel(SeasoningRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Seasoning updateFromDto(SeasoningRequestDto dto, @MappingTarget Seasoning.SeasoningBuilder builder);

    // Métodos auxiliares para conversión de enum
    @Named("fromAbbreviation")
    static UnitType mapUnit(String abbreviation) {
        return UnitType.fromAbbreviation(abbreviation);
    }

    @Named("toAbbreviation")
    static String mapUnit(UnitType unitType) {
        return unitType != null ? unitType.getAbbreviation() : null;
    }
}

