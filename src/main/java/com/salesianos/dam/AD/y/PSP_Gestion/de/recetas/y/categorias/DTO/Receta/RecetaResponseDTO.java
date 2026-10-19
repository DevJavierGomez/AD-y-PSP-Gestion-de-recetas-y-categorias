package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Receta;

import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Categoria.CategoriaSimpleDTO;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Ingrediente.IngredienteSimpleDTO;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Dificultad;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Receta;

import java.util.List;

public record RecetaResponseDTO(
        Long id,
        String nombre,
        Integer tiempoPreparacionMin,
        Dificultad dificultad,
        CategoriaSimpleDTO categoria,
        List<IngredienteSimpleDTO> ingredientes
) {

    public static RecetaResponseDTO of(Receta r) {
        return new RecetaResponseDTO(
                r.getId(),
                r.getNombre(),
                r.getTiempoPreparacionMin(),
                r.getDificultad(),
                CategoriaSimpleDTO.of(r.getCategoria()),
                r.getIngredientes().stream()
                        .map(IngredienteSimpleDTO::of)
                        .toList()
        );
    }

}
