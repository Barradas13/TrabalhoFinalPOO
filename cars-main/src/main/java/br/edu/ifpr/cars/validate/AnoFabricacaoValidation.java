package br.edu.ifpr.cars.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AnoFabricacaoValidation implements ConstraintValidator<AnoFabricacao, String>{

    @Override
    public boolean isValid(String anoFabricacao, ConstraintValidatorContext context) {
        return Integer.parseInt(anoFabricacao) >= 1886;
    }


}
