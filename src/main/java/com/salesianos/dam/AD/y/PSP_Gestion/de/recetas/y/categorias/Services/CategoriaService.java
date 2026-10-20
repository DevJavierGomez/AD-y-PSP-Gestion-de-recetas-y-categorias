package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Services;

import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Repository.CategoriaRepository;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class CategoriaService {

        private final CategoriaRepository categoriaRepository;

        public Categoria addCategoria(Categoria categoria) {
            return categoriaRepository.save(categoria);
        }

        public List<Categoria> getAll() {
            return categoriaRepository.findAll();
        }

        public Categoria getById(Long id) {
            return categoriaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        }

        public Categoria updateCategoria(Long id, Categoria categoria) {
            if (!categoriaRepository.existsById(id)) {
                throw new RuntimeException("Categoría no encontrada");
            }
            categoria.setId(id);
            return categoriaRepository.save(categoria);
        }

        public void deleteCategoria(Long id) {
            categoriaRepository.deleteById(id);
        }

    }
