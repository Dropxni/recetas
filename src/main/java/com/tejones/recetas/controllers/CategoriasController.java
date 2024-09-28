package com.tejones.recetas.controllers;

import com.tejones.recetas.models.Categoria;
import com.tejones.recetas.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listCategorias(Model model) {
        List<Categoria> categorias = categoriaService.listarTodas();
        model.addAttribute("categorias", categorias);
        return "categorias/list";
    }

    // Agregar métodos para ver recetas por categoría si es necesario
}
