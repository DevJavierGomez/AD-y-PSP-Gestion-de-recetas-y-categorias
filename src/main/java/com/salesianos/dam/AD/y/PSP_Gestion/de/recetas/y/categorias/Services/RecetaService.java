package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Services;

import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Repository.CategoriaRepository;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Repository.RecetaRepository;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Categoria;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Receta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final CategoriaRepository categoriaRepository;

    public Receta addReceta(Receta receta, Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElse(null);

        if (categoria == null) {
            return null;
        }

        receta.setCategoria(categoria);

        return recetaRepository.save(receta);

    }

    public List<Receta> getAll() {
        List<Receta> recetas = recetaRepository.findAll();
        if (recetas.isEmpty()) {
            throw new RuntimeException("No se encontraron recetas");
        }

        return recetas;
    }

    public Receta getById(Long id) {
        return recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con el ID: " + id));
    }

    public Receta updateReceta(Long id, Receta recetaModificada, Long categoriaId) {
        Receta recetaExistente = recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la receta con el ID: " + id));

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("No se encontró la categoría con el ID: " + categoriaId));

        recetaExistente.setNombre(recetaModificada.getNombre());
        recetaExistente.setTiempoPreparacionMin(recetaModificada.getTiempoPreparacionMin());
        recetaExistente.setDificultad(recetaModificada.getDificultad());
        recetaExistente.setCategoria(categoria);

        return recetaRepository.save(recetaExistente);
    }

    public void deleteReceta(Long id) {
        if (!recetaRepository.existsById(id)) {
            throw new RuntimeException("Receta no encontrada con el ID: " + id);
        }

        recetaRepository.deleteById(id);

    }

}
