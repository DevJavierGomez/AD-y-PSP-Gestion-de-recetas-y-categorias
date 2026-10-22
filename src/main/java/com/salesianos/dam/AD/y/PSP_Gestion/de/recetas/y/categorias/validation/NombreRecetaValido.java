package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NombreRecetaValidoValidator.class)
@Documented
public @interface NombreRecetaValido {

    String message() default "El nombre de la receta no es válido";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}