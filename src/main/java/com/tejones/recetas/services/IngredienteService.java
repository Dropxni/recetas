package com.tejones.recetas.services;

import com.tejones.recetas.models.Ingrediente;
import java.util.List;

public interface IngredienteService {
    List<Ingrediente> listarTodos();
    Ingrediente obtenerIngredientePorId(Long id);
}
