package com.tejones.recetas.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany(mappedBy = "ingredientes")
    private List<Receta> recetas;

    // Getters y Setters
}
