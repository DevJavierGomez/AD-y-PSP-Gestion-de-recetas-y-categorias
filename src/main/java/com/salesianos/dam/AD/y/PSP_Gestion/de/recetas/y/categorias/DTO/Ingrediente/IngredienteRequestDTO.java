package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Ingrediente;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(
        name = "IngredienteRequestDTO",
        description = "Datos requeridos para registrar nuevo ingrediente"
)

public record IngredienteRequestDTO(
        @NotBlank(message = "El nombre del ingrediente no puede estar vacío")
        String nombre
) {}