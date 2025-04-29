package com.taskManager.taskManager.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailOrNoneValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailOrNone {

    String message() default "The value must be a valid email address or the string 'none'.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
