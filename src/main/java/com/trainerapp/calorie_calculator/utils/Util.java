package com.trainerapp.calorie_calculator.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util {

    public static double formatPercentage(double percentage) {
        if (percentage >= 1) {
            return Math.round(percentage);
        } else {
            return BigDecimal.valueOf(percentage)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        }
    }
}
