package com.tejones.recetas.services;

import com.tejones.recetas.models.Categoria;
import java.util.List;

public interface CategoriaService {
    List<Categoria> listarTodas();
    void guardarCategoria(Categoria categoria);
    Categoria obtenerCategoriaPorId(Long id);
    void eliminarCategoria(Long id);
}
