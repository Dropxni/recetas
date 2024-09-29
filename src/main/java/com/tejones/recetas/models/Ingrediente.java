package com.tejones.recetas.models;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ingredientes")
public class Ingrediente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    // Getters and Setters
}
