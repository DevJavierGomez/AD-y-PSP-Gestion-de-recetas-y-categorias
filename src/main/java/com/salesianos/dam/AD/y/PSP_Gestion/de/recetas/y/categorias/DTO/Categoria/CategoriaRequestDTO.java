package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Categoria;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(
        name = "CategoriaRequestDTO",
        description = "Datos requeridos para registrar nueva categoria"
)

public record CategoriaRequestDTO(
        @NotBlank(message = "El nombre de la categoría no puede estar vacío")
        String nombre,

        String descripcion
) {}