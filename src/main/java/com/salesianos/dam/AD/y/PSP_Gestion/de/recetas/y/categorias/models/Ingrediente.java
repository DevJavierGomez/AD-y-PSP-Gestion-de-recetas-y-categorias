package com.salesianos.dam.AD.y.PSP_Gestion.de.recetas.y.categorias.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Ingrediente {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String nombre;

}
