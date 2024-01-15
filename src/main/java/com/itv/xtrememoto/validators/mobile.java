package com.itv.xtrememoto.validators;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = MobileValidators.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface mobile {
    String message() default "invalid mobile number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}