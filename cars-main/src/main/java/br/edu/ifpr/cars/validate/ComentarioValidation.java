package br.edu.ifpr.cars.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ComentarioValidation implements ConstraintValidator<Comentario, String>{

    @Override
    public boolean isValid(String comentario, ConstraintValidatorContext context) {
        
        String[] palavrasProibidas = {"bobao", "boco", "mane"};
        
        for (String string : palavrasProibidas) {
            if(comentario.contains(string)) return false;
        }

        return true;
    }


}
