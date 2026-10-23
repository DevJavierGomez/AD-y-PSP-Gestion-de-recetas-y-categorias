package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Controller;

import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Ingrediente.IngredienteRequestDTO;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Ingrediente.IngredienteSimpleDTO;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Services.IngredienteService;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Ingrediente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Ingredientes", description = "Gestión del catálogo de ingredientes disponibles")
@RestController
@RequestMapping("/api/v1/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {

    private final IngredienteService ingredienteService;

    @Operation(summary = "Crear ingrediente", description = "Añade un nuevo ingrediente al catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ingrediente creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos")
    })
    @PostMapping
    public ResponseEntity<IngredienteSimpleDTO> addIngrediente(@Valid @RequestBody IngredienteRequestDTO dto) {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre(dto.nombre());

        Ingrediente guardado = ingredienteService.addIngrediente(ingrediente);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(IngredienteSimpleDTO.of(guardado));
    }

    @Operation(summary = "Obtener todos los ingredientes", description = "Devuelve el listado completo de ingredientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ingredientes obtenida con éxito")
    })
    @GetMapping
    public ResponseEntity<List<IngredienteSimpleDTO>> getAll() {
        List<IngredienteSimpleDTO> lista = ingredienteService.getAll()
                .stream()
                .map(IngredienteSimpleDTO::of)
                .toList();

        return ResponseEntity.ok(lista);
    }
}