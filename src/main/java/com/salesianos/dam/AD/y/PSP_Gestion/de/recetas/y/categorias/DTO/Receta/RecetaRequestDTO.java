package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Receta;

import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Dificultad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RecetaRequestDTO(
        @NotBlank(message = "El nombre de la receta no puede estar vacío")
        @Size(min = 3, message = "El nombre de la receta debe tener al menos 3 caracteres")
        String nombre,

        @NotNull(message = "El tiempo de preparación es obligatorio")
        Integer tiempoPreparacionMin,

        @NotNull(message = "La dificultad es obligatoria")
        Dificultad dificultad,

        @NotNull(message = "La categoría es obligatoria")
        Long categoriaId
) {}