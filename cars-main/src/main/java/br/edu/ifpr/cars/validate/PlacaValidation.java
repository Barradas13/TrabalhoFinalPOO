package br.edu.ifpr.cars.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PlacaValidation implements ConstraintValidator<Placa, String>{

    @Override
    public boolean isValid(String placa, ConstraintValidatorContext context) {
        return placa.matches("^[A-Z]{3}[0-9][A-Z][0-9]{2}$");
    }


}
