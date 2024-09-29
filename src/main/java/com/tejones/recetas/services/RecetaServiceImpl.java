package com.tejones.recetas.services;

import com.tejones.recetas.models.Receta;
import com.tejones.recetas.repositories.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    @Override
    public List<Receta> listarRecetas() {
        return recetaRepository.findAll();
    }

    @Override
    public void guardarReceta(Receta receta) {
        recetaRepository.save(receta);
    }

    @Override
    public Receta obtenerRecetaPorId(Long id) {
        return recetaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarReceta(Long id) {
        recetaRepository.deleteById(id);
    }
}
