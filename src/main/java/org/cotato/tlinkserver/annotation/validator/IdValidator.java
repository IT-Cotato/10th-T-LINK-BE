package org.cotato.tlinkserver.annotation.validator;

import org.cotato.tlinkserver.annotation.IdValidation;
import org.cotato.tlinkserver.global.exception.BadRequestException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdValidator implements ConstraintValidator<IdValidation, Long> {
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value > 0) {
            return true;
        }
        throw BadRequestException.wrong();
    }
}