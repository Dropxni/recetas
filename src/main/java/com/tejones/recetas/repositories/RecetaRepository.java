package com.tejones.recetas.repositories;

import com.tejones.recetas.models.Receta;
import com.tejones.recetas.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Long> {

    List<Receta> findByCategoria(Categoria categoria); // Método que buscará recetas por categoría
}