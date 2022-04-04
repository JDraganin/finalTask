package com.example.finaltask.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PaymentTypeValidator implements ConstraintValidator<com.example.finaltask.validation.PaymentType,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("CASH")||value.equals("CARD");
    }
}