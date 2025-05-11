package org.cotato.tlinkserver.annotation;

import jakarta.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.cotato.tlinkserver.annotation.validator.IdValidator;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdValidator.class)
public @interface IdValidation {
    String message() default "요청 형식이 올바르지 않습니다.";

    Class[] groups() default {};

    Class[] payload() default {};
}