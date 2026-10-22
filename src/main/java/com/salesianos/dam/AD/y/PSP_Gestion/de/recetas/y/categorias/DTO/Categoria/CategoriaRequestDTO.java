package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Categoria;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequestDTO(
        @NotBlank(message = "El nombre de la categoría no puede estar vacío")
        String nombre,

        String descripcion
) {}