package com.tejones.recetas.services;

import com.tejones.recetas.models.Categoria;
import com.tejones.recetas.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository; // Importar y utilizar el repositorio

    @Override
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll(); // Usar el repositorio para listar todas las categorías
    }

    @Override
    public void guardarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria); // Guardar una categoría
    }

    @Override
    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null); // Obtener categoría por id
    }

    @Override
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id); // Eliminar categoría por id
    }
}
