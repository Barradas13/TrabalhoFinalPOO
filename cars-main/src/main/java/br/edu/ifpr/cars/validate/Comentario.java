package br.edu.ifpr.cars.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
@Documented
@Constraint(validatedBy = ComentarioValidation.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Comentario {
    String message() default "não pode ser ofensivo";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
