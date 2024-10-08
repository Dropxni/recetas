package com.tejones.recetas.services;

import com.tejones.recetas.models.Ingrediente;
import com.tejones.recetas.repositories.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IngredienteServiceImpl implements IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Override
    public List<Ingrediente> listarTodos() {
        return ingredienteRepository.findAll();
    }

    @Override
    public Ingrediente obtenerIngredientePorId(Long id) {
        return ingredienteRepository.findById(id).orElse(null);
    }
}
