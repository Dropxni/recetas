package com.tejones.recetas.services;

import com.tejones.recetas.models.Receta;
import com.tejones.recetas.repositories.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    public List<Receta> listarTodas() {
        return recetaRepository.findAll();
    }

    public Receta obtenerPorId(Long id) {
        return recetaRepository.findById(id).orElse(null);
    }

    public Receta guardar(Receta receta) {
        return recetaRepository.save(receta);
    }

    public void eliminar(Long id) {
        recetaRepository.deleteById(id);
    }
}
