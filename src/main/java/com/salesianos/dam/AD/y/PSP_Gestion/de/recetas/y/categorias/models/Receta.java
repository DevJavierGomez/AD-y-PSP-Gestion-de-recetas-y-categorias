package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Receta {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String nombre;

    private Integer tiempoPreparacionMin;

    @Enumerated(EnumType.STRING)
    private Dificultad dificultad;

    @ManyToOne
    private Categoria categoria;

    @ManyToMany
    @Builder.Default
    private List<Ingrediente> ingredientes = new ArrayList<>();

}
