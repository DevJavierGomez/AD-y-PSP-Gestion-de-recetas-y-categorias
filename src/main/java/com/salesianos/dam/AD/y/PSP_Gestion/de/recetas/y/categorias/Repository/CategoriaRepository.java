package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Repository;

import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
