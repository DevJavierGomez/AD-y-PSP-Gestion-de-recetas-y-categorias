package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Ingrediente;

import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Ingrediente;

public record IngredienteSimpleDTO(
        Long id,
        String nombre
) {

    public static IngredienteSimpleDTO of(Ingrediente i) {
        return new IngredienteSimpleDTO(
                i.getId(),
                i.getNombre()
        );
    }

}
