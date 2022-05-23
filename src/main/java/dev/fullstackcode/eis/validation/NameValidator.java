package dev.fullstackcode.eis.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<ValidName,String> {
    @Override
    public void initialize(ValidName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if(name == null) {
            return true;
        }
        if(name.matches("^[a-zA-Z\\s]*$")) {
            return true;
        }
        return false;
    }
}
