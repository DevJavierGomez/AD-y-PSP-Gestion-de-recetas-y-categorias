package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Controller;

import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Ingrediente.AnadirIngredienteRequestDTO;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Receta.RecetaResponseDTO;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Services.RecetaService;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Receta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recetas")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;

    @PostMapping("/categorias/{categoriaId}")
    public ResponseEntity<Receta> createReceta(@RequestBody Receta receta, @PathVariable Long categoriaId) {
        Receta nuevaReceta = recetaService.addReceta(receta, categoriaId);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReceta);
    }

    @GetMapping
    public ResponseEntity<List<Receta>> getAllRecetas() {
        List<Receta> recetas = recetaService.getAll();
        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/{recetaId}")
    public ResponseEntity<Receta> getRecetaById(@PathVariable Long recetaId) {
        Receta receta = recetaService.getById(recetaId);
        return ResponseEntity.ok(receta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receta> updateReceta( @PathVariable Long id, @Valid @RequestBody Receta receta,
            @RequestParam Long categoriaId) {

        Receta recetaActualizada = recetaService.updateReceta(id, receta, categoriaId);
        return ResponseEntity.ok(recetaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceta(@PathVariable Long id) {
        recetaService.deleteReceta(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{recetaId}/ingredientes")
    public ResponseEntity<Receta> anadirIngrediente( @Valid @PathVariable Long recetaId,
            @RequestBody AnadirIngredienteRequestDTO dto) {

        Receta recetaActualizada = recetaService.addIngrediente(recetaId, dto.ingredienteId());

        RecetaResponseDTO respuesta = RecetaResponseDTO.of(recetaActualizada);

        return ResponseEntity.ok(recetaActualizada);
    }

}
