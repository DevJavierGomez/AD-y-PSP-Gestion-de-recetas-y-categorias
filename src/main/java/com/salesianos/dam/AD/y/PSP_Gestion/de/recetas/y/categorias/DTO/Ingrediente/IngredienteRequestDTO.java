package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Ingrediente;

import jakarta.validation.constraints.NotBlank;

public record IngredienteRequestDTO(
        @NotBlank(message = "El nombre del ingrediente no puede estar vacío")
        String nombre
) {}