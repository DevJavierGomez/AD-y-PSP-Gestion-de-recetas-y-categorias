package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Controller;

import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Categoria.CategoriaRequestDTO;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Categoria.CategoriaResponseDTO;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Services.CategoriaService;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Categoria;
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

@Tag(name = "Categorías", description = "Gestión de las categorías de las recetas")
@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Operation(summary = "Crear nueva categoría", description = "Registra una nueva categoría en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos")
    })
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> addCategoria(@Valid @RequestBody CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.nombre());
        categoria.setDescripcion(dto.descripcion());

        Categoria guardada = categoriaService.addCategoria(categoria);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoriaResponseDTO.of(guardada));
    }

    @Operation(summary = "Obtener todas las categorías", description = "Devuelve un listado completo de categorías")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida con éxito")
    })
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> getAll() {
        List<CategoriaResponseDTO> lista = categoriaService.getAll()
                .stream()
                .map(CategoriaResponseDTO::of)
                .toList();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Obtener categoría por ID", description = "Busca una categoría específica según su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> getById(@PathVariable Long id) {
        Categoria categoria = categoriaService.getById(id);
        return ResponseEntity.ok(CategoriaResponseDTO.of(categoria));
    }

    @Operation(summary = "Actualizar categoría", description = "Modifica los datos de una categoría existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de actualización no válidos"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> updateCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.nombre());
        categoria.setDescripcion(dto.descripcion());

        Categoria actualizada = categoriaService.updateCategoria(id, categoria);

        return ResponseEntity.ok(CategoriaResponseDTO.of(actualizada));
    }

    @Operation(summary = "Eliminar categoría", description = "Elimina una categoría del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoría eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}