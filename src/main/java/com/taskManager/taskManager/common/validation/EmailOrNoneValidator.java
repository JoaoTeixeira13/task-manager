package com.taskManager.taskManager.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

public class EmailOrNoneValidator implements ConstraintValidator<EmailOrNone, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }

        if ("none".equalsIgnoreCase(value)) {
            return true;
        }

        return new EmailValidator().isValid(value, context);
    }
}
