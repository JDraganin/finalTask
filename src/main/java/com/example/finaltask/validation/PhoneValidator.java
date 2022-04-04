package com.example.finaltask.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.length() == 10 && value.startsWith("087") || value.startsWith("088") || value.startsWith("089");
    }
}