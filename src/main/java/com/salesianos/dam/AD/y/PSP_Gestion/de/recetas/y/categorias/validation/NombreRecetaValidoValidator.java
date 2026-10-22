package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NombreRecetaValidoValidator implements ConstraintValidator<NombreRecetaValido, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() < 3) {
            return false;
        }

        return true;
    }
}