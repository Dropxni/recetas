package com.tejones.recetas.services;

import com.tejones.recetas.models.Receta;

import java.util.List;

public interface RecetaService {

    List<Receta> listarRecetas();

    void guardarReceta(Receta receta);

    Receta obtenerRecetaPorId(Long id);

    void eliminarReceta(Long id);
}
