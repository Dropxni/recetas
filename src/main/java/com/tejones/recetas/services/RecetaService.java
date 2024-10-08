package com.tejones.recetas.services;

import com.tejones.recetas.models.Receta;
import com.tejones.recetas.models.Categoria;
import java.util.List;

public interface RecetaService {

    List<Receta> listarRecetas();

    List<Receta> listarRecetasPorCategoria(Categoria categoria); // Método añadido

    void guardarReceta(Receta receta);

    Receta obtenerRecetaPorId(Long id);

    void eliminarReceta(Long id);
}
