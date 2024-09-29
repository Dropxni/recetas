package com.tejones.recetas.models;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categorias")
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(nullable = true)
    private String descripcion;

    // Getters and Setters
}
