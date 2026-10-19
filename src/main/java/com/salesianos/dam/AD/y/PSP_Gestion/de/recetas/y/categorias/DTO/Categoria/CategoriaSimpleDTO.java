package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Categoria;

import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Categoria;

public record CategoriaSimpleDTO(
        Long id,
        String nombre
) {

    public static CategoriaSimpleDTO of(Categoria c) {
        return new CategoriaSimpleDTO(
                c.getId(),
                c.getNombre()
        );
    }

}
