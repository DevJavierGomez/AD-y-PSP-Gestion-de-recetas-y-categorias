package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Services;

import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.Repository.IngredienteRepository;
import com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models.Ingrediente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    public Ingrediente addIngrediente(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    public List<Ingrediente> getAll() {
        return ingredienteRepository.findAll();
    }

}
