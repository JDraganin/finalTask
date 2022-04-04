package com.example.finaltask.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ItemTypeValidator implements ConstraintValidator<ItemType, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("DRINKS") || value.equals("FOOD") || value.equals("TECHNOLOGY") || value.equals("HOUSEHOLD");
    }
}