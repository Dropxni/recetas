package com.tejones.recetas.controllers;

import com.tejones.recetas.models.Ingrediente;
import com.tejones.recetas.models.Receta;
import com.tejones.recetas.models.RecetaIngrediente;
import com.tejones.recetas.services.IngredienteService;
import com.tejones.recetas.services.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;



import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/recetas")
public class RecetasController {

    @Autowired
    private RecetaService recetaService;

    @Autowired
    private IngredienteService ingredienteService;

    // Redirigir a la lista de recetas cuando se accede a /recetas
    @GetMapping
    public String redirigirALista() {
        return "redirect:/recetas/list";
    }

    // Listar todas las recetas
    @GetMapping("/list")
    public String listarRecetas(Model model) {
        List<Receta> recetas = recetaService.listarRecetas();
        model.addAttribute("recetas", recetas);
        return "recetas/list"; // Página donde se listan las recetas
    }

    // Mostrar el formulario para crear una nueva receta
    @GetMapping("/nueva")
    public String mostrarFormularioNuevaReceta(Model model) {
        Receta receta = new Receta();
        List<Ingrediente> ingredientesDisponibles = ingredienteService.listarTodos();
        model.addAttribute("receta", receta);
        model.addAttribute("ingredientesDisponibles", ingredientesDisponibles); // Lista de ingredientes existentes
        return "recetas/form"; // Página donde se muestra el formulario
    }

    // Guardar una nueva receta o actualizar una existente con imagen y manejo de ingredientes
    @PostMapping("/guardar")
    public String guardarReceta(@ModelAttribute("receta") Receta receta,
                                @RequestParam(value = "imagenUrl", required = false) String imagenUrl,
                                @RequestParam(value = "ingredienteIds", required = false) List<Long> ingredienteIds,
                                @RequestParam(value = "cantidades", required = false) List<String> cantidades) {
        // Asignar la URL de la imagen directamente
        receta.setImagenUrl(imagenUrl);

        // Verificar si hay ingredientes asociados
        if (ingredienteIds != null && cantidades != null && ingredienteIds.size() == cantidades.size()) {
            List<RecetaIngrediente> recetaIngredientes = new ArrayList<>();
            for (int i = 0; i < ingredienteIds.size(); i++) {
                Ingrediente ingrediente = ingredienteService.obtenerIngredientePorId(ingredienteIds.get(i));
                RecetaIngrediente recetaIngrediente = new RecetaIngrediente();
                recetaIngrediente.setIngrediente(ingrediente);
                recetaIngrediente.setCantidad(cantidades.get(i));
                recetaIngrediente.setReceta(receta);
                recetaIngredientes.add(recetaIngrediente);
            }
            receta.setIngredientes(recetaIngredientes);
        }

        recetaService.guardarReceta(receta);
        return "redirect:/recetas/list";
    }

    // Mostrar el formulario para editar una receta existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarReceta(@PathVariable("id") Long id, Model model) {
        Receta receta = recetaService.obtenerRecetaPorId(id);
        List<Ingrediente> ingredientesDisponibles = ingredienteService.listarTodos(); // Cargar ingredientes disponibles
        if (receta != null) {
            model.addAttribute("receta", receta);
            model.addAttribute("ingredientesDisponibles", ingredientesDisponibles); // Enviar ingredientes a la vista
            return "recetas/form"; // Reutiliza la misma vista para editar
        } else {
            return "redirect:/recetas/list";
        }
    }

    // Eliminar una receta
    @GetMapping("/eliminar/{id}")
    public String eliminarReceta(@PathVariable("id") Long id) {
        recetaService.eliminarReceta(id);
        return "redirect:/recetas/list";
    }

    // Mostrar detalles de una receta
    @GetMapping("/detalle/{id}")
    public String verDetallesReceta(@PathVariable("id") Long id, Model model) {
        Receta receta = recetaService.obtenerRecetaPorId(id);
        if (receta != null) {
            // Dividir el procedimiento en una lista de pasos utilizando ". " como delimitador
            List<String> pasos = Arrays.asList(receta.getProcedimiento().split("\\d+\\.\\s*"));
            pasos = pasos.stream().filter(paso -> !paso.isEmpty()).toList(); // Filtrar cadenas vacías

            model.addAttribute("receta", receta);
            model.addAttribute("pasos", pasos); // Enviar los pasos a la vista
            return "recetas/detalle"; // Página para mostrar los detalles de la receta
        } else {
            return "redirect:/recetas/list";
        }
    }
}
