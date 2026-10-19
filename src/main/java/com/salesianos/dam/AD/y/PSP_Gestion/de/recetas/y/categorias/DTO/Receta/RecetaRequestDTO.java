package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Receta;

import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Dificultad;

public record RecetaRequestDTO(
        String nombre,
        Integer tiempoPreparacionMin,
        Dificultad dificultad,
        Long categoriaId
) {
}
