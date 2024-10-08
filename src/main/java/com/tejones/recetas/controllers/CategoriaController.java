package com.tejones.recetas.controllers;

import com.tejones.recetas.models.Categoria;
import com.tejones.recetas.models.Receta;
import com.tejones.recetas.services.CategoriaService;
import com.tejones.recetas.services.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private RecetaService recetaService;

    // Listar todas las categorías
    @GetMapping("/list")
    public String listarCategorias(Model model) {
        List<Categoria> categorias = categoriaService.listarTodas();
        model.addAttribute("categorias", categorias);
        return "categorias/list"; // Página para listar las categorías
    }

    // Ver recetas por categoría
    @GetMapping("/{id}/recetas")
    public String verRecetasPorCategoria(@PathVariable("id") Long id, Model model) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
        if (categoria != null) {
            List<Receta> recetas = recetaService.listarRecetasPorCategoria(categoria);
            model.addAttribute("recetas", recetas);
            model.addAttribute("categoria", categoria);
            return "categorias/recetas"; // Página que muestra las recetas de la categoría
        } else {
            return "redirect:/categorias/list";
        }
    }

    // Mostrar formulario para agregar una nueva categoría
    @GetMapping("/nueva")
    public String mostrarFormularioNuevaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/form"; // Página del formulario de categoría
    }

    // Guardar una nueva categoría
    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaService.guardarCategoria(categoria);
        return "redirect:/categorias/list";
    }

    // Mostrar formulario para editar una categoría existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCategoria(@PathVariable("id") Long id, Model model) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
        if (categoria != null) {
            model.addAttribute("categoria", categoria);
            return "categorias/form";
        } else {
            return "redirect:/categorias/list";
        }
    }

    // Eliminar una categoría
    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable("id") Long id) {
        categoriaService.eliminarCategoria(id);
        return "redirect:/categorias/list";
    }
}
