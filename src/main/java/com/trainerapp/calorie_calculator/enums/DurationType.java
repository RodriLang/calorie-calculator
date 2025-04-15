package com.trainerapp.calorie_calculator.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DurationType {

    DAY("día", "días"),
    WEEK("semana", "semanas"),
    MONTH("mes", "meses"),
    YEAR("año", "años"),
    HOUR("h", "hs"),
    MINUTE("min", "min"),
    SECOND("seg", "seg");

    private final String singularAbbreviation;
    private final String pluralAbbreviation;

    /**
     * Devuelve la abreviación en singular o plural según corresponda.
     * @param quantity La cantidad de la unidad de tiempo.
     * @return La abreviación adecuada en singular o plural.
     */
    public String getAbbreviationBasedOnQuantity(long quantity) {
        // Si la cantidad es 1, usar singular; si es mayor que 1, usar plural.
        return quantity == 1 ? singularAbbreviation : pluralAbbreviation;
    }

}
