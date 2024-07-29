package com.example.car_sale.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

public class YearLimitValidator implements ConstraintValidator<YearLimit, Integer> {
    private int min;
    private int max;

    @Override
    public void initialize(YearLimit constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value  >= min && value <= max;
    }


}
