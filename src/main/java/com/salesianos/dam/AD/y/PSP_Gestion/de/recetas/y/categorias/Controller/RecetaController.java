package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Controller;

import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Ingrediente.AnadirIngredienteRequestDTO;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Receta.RecetaResponseDTO;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Services.RecetaService;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Receta;
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

@Tag(name = "Recetas", description = "Operaciones para la gestión de recetas de cocina y sus ingredientes")
@RestController
@RequestMapping("/api/v1/recetas")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;

    @Operation(summary = "Crear nueva receta", description = "Registra una receta asignándola a una categoría existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Receta creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Tiempo de preparación no válido o datos incorrectos"),
            @ApiResponse(responseCode = "404", description = "Categoría especificada no encontrada")
    })
    @PostMapping("/categorias/{categoriaId}")
    public ResponseEntity<Receta> createReceta(@RequestBody Receta receta, @PathVariable Long categoriaId) {
        Receta nuevaReceta = recetaService.addReceta(receta, categoriaId);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReceta);
    }

    @Operation(summary = "Obtener todas las recetas", description = "Devuelve el listado completo de recetas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de recetas obtenida con éxito")
    })
    @GetMapping
    public ResponseEntity<List<Receta>> getAllRecetas() {
        List<Receta> recetas = recetaService.getAll();
        return ResponseEntity.ok(recetas);
    }

    @Operation(summary = "Obtener receta por ID", description = "Busca una receta específica mediante su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receta encontrada"),
            @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    @GetMapping("/{recetaId}")
    public ResponseEntity<Receta> getRecetaById(@PathVariable Long recetaId) {
        Receta receta = recetaService.getById(recetaId);
        return ResponseEntity.ok(receta);
    }

    @Operation(summary = "Actualizar receta", description = "Modifica los datos de una receta existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receta actualizada con éxito"),
            @ApiResponse(responseCode = "400", description = "Tiempo de preparación no válido o datos de entrada incorrectos"),
            @ApiResponse(responseCode = "404", description = "Receta o Categoría no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Receta> updateReceta(@PathVariable Long id, @Valid @RequestBody Receta receta,
                                               @RequestParam Long categoriaId) {

        Receta recetaActualizada = recetaService.updateReceta(id, receta, categoriaId);
        return ResponseEntity.ok(recetaActualizada);
    }

    @Operation(summary = "Eliminar receta", description = "Elimina una receta del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Receta eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceta(@PathVariable Long id) {
        recetaService.deleteReceta(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Asociar ingrediente a una receta",
            description = "Añade un ingrediente existente a una receta mediante un JSON que contiene el `ingredienteId`"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingrediente asociado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos"),
            @ApiResponse(responseCode = "404", description = "Receta o Ingrediente no encontrado"),
            @ApiResponse(responseCode = "409", description = "El ingrediente ya está asociado a la receta")
    })
    @PostMapping("/{recetaId}/ingredientes")
    public ResponseEntity<Receta> anadirIngrediente(@Valid @PathVariable Long recetaId,
                                                    @RequestBody AnadirIngredienteRequestDTO dto) {

        Receta recetaActualizada = recetaService.addIngrediente(recetaId, dto.ingredienteId());

        RecetaResponseDTO respuesta = RecetaResponseDTO.of(recetaActualizada);

        return ResponseEntity.ok(recetaActualizada);
    }

}