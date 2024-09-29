package com.tejones.recetas.models;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RecetaIngredienteId implements Serializable {

    private Long recetaId;
    private Long ingredienteId;

    // Constructores, getters, setters, hashCode y equals

    public RecetaIngredienteId() {
    }

    public RecetaIngredienteId(Long recetaId, Long ingredienteId) {
        this.recetaId = recetaId;
        this.ingredienteId = ingredienteId;
    }

    public Long getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(Long recetaId) {
        this.recetaId = recetaId;
    }

    public Long getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(Long ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecetaIngredienteId that = (RecetaIngredienteId) o;
        return Objects.equals(recetaId, that.recetaId) &&
               Objects.equals(ingredienteId, that.ingredienteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recetaId, ingredienteId);
    }
}
