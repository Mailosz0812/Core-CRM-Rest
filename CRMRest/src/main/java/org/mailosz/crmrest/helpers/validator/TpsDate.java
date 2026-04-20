package org.mailosz.crmrest.helpers.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TpsDateValidator.class)
public @interface TpsDate {
    String message() default "INVALID_TPS";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
