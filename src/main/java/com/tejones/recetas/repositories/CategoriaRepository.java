package com.tejones.recetas.repositories;

import com.tejones.recetas.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Aquí puedes agregar métodos de consulta personalizados si es necesario
}
