package com.cme.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.validation.constraintvalidation.ValidationTarget.ANNOTATED_ELEMENT;

@Constraint(validatedBy = PalindromeValueValidator.class)
@Target({
        TYPE,
        FIELD,
        ANNOTATION_TYPE
})
@Retention(RUNTIME)
@Documented
@SupportedValidationTarget(ANNOTATED_ELEMENT)
public @interface ValidatePalindrome {

    String message() default "";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}