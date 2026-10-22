package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Controller;

import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Ingrediente.IngredienteRequestDTO;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.DTO.Ingrediente.IngredienteSimpleDTO;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Services.IngredienteService;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Ingrediente;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {

    private final IngredienteService ingredienteService;

    @PostMapping
    public ResponseEntity<IngredienteSimpleDTO> addIngrediente(@Valid @RequestBody IngredienteRequestDTO dto) {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre(dto.nombre());

        Ingrediente guardado = ingredienteService.addIngrediente(ingrediente);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(IngredienteSimpleDTO.of(guardado));

    }

    @GetMapping
    public ResponseEntity<List<IngredienteSimpleDTO>> getAll() {
        List<IngredienteSimpleDTO> lista = ingredienteService.getAll()
                .stream()
                .map(IngredienteSimpleDTO::of)
                .toList();

        return ResponseEntity.ok(lista);
    }

}
