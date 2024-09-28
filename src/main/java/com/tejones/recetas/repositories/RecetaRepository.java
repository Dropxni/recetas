package com.tejones.recetas.repositories;

import com.tejones.recetas.models.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
    // Métodos personalizados si es necesario
}
