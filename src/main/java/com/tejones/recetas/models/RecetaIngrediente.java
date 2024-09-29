package com.tejones.recetas.models;

import jakarta.persistence.*;

@Entity
@Table(name = "receta_ingredientes")
public class RecetaIngrediente {

    @EmbeddedId
    private RecetaIngredienteId id;

    @ManyToOne
    @MapsId("recetaId")
    @JoinColumn(name = "receta_id")
    private Receta receta;

    @ManyToOne
    @MapsId("ingredienteId")
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    private String cantidad;

    public RecetaIngredienteId getId() {
        return id;
    }

    public void setId(RecetaIngredienteId id) {
        this.id = id;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
