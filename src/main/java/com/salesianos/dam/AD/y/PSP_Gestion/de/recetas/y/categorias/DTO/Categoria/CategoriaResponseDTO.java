package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Categoria;

import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Categoria;

public record CategoriaResponseDTO(
        Long id,
        String nombre,
        String descripcion
) {

    public static CategoriaResponseDTO of(Categoria c) {
        return new CategoriaResponseDTO(
                c.getId(),
                c.getNombre(),
                c.getDescripcion()
        );
    }

}
