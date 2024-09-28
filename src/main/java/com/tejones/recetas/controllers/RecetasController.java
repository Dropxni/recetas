package com.tejones.recetas.controllers;

import com.tejones.recetas.models.Receta;
import com.tejones.recetas.services.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/recetas")
public class RecetasController {

    @Autowired
    private RecetaService recetaService;

    @GetMapping
    public String listRecetas(Model model) {
        List<Receta> recetas = recetaService.listarTodas();
        model.addAttribute("recetas", recetas);
        return "recetas/list";
    }

    @GetMapping("/{id}")
    public String detalleReceta(@PathVariable Long id, Model model) {
        Receta receta = recetaService.obtenerPorId(id);
        if (receta == null) {
            return "redirect:/recetas";
        }
        model.addAttribute("receta", receta);
        return "recetas/detalle";
    }

    // Métodos para crear, editar y eliminar recetas pueden agregarse aquí
}
