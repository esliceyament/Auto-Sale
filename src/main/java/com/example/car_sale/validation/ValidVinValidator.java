package com.example.car_sale.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidVinValidator implements ConstraintValidator<ValidVin, String> {

    private String pattern;

    @Override
    public void initialize(ValidVin constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.matches(pattern);
    }
}