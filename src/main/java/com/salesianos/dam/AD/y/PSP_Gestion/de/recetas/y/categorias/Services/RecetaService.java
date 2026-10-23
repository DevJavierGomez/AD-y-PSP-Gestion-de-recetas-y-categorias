package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Services;

import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Repository.CategoriaRepository;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Repository.IngredienteRepository;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Repository.RecetaRepository;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.error.*;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Categoria;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Ingrediente;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Receta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final CategoriaRepository categoriaRepository;
    private final IngredienteRepository ingredienteRepository;

    public Receta addReceta(Receta receta, Long categoriaId) {
        if (receta.getTiempoPreparacionMin() != null && receta.getTiempoPreparacionMin() <= 0) {
            throw new TiempoInvalidoException("El tiempo de preparación debe ser mayor que cero");
        }

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new CategoriaNoEncontradaException("Categoría no encontrada con el ID: " + categoriaId));

        receta.setCategoria(categoria);
        return recetaRepository.save(receta);
    }

    public List<Receta> getAll() {
        return recetaRepository.findAll();
    }

    public Receta getById(Long id) {
        return recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNoEncontradaException("Receta no encontrada con el ID: " + id));
    }

    public Receta updateReceta(Long id, Receta recetaModificada, Long categoriaId) {
        if (recetaModificada.getTiempoPreparacionMin() != null && recetaModificada.getTiempoPreparacionMin() <= 0) {
            throw new TiempoInvalidoException("El tiempo de preparación debe ser mayor que cero");
        }

        Receta recetaExistente = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNoEncontradaException("No se encontró la receta con el ID: " + id));

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new CategoriaNoEncontradaException("No se encontró la categoría con el ID: " + categoriaId));

        recetaExistente.setNombre(recetaModificada.getNombre());
        recetaExistente.setTiempoPreparacionMin(recetaModificada.getTiempoPreparacionMin());
        recetaExistente.setDificultad(recetaModificada.getDificultad());
        recetaExistente.setCategoria(categoria);

        return recetaRepository.save(recetaExistente);
    }

    public void deleteReceta(Long id) {
        if (!recetaRepository.existsById(id)) {
            throw new RecetaNoEncontradaException("Receta no encontrada con el ID: " + id);
        }

        recetaRepository.deleteById(id);
    }

    public Receta addIngrediente(Long recetaId, Long ingredienteId) {
        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new RecetaNoEncontradaException("Receta no encontrada con el ID: " + recetaId));

        Ingrediente ingrediente = ingredienteRepository.findById(ingredienteId)
                .orElseThrow(() -> new IngredienteNoEncontradoException("Ingrediente no encontrado con el ID: " + ingredienteId));

        if (receta.getIngredientes().contains(ingrediente)) {
            throw new IngredienteYaAnadidoException("El ingrediente ya está añadido a esta receta");
        }

        receta.getIngredientes().add(ingrediente);
        return recetaRepository.save(receta);
    }
}