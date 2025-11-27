package br.edu.ifpr.cars.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CnhValidation implements ConstraintValidator<CNH, String>{

    @Override
    public boolean isValid(String cnh, ConstraintValidatorContext context) {
        return cnh.length() == 11;
    }


}
