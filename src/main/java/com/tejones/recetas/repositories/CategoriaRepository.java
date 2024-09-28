package com.tejones.recetas.repositories;

import com.tejones.recetas.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Métodos personalizados si es necesario
}
