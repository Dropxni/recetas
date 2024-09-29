package com.tejones.recetas.controllers;

import com.tejones.recetas.models.Receta;
import com.tejones.recetas.services.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/recetas")
public class RecetasController {

    @Autowired
    private RecetaService recetaService;

    // Ruta donde se guardarán las imágenes
    private final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

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
        model.addAttribute("receta", receta);
        return "recetas/form"; // Página donde se muestra el formulario
    }

    // Guardar una nueva receta o actualizar una existente con imagen
    @PostMapping("/guardar")
    public String guardarReceta(@ModelAttribute("receta") Receta receta,
                                @RequestParam("imagen") MultipartFile imagen) {
        if (!imagen.isEmpty()) {
            try {
                // Guardar la imagen en el directorio de uploads
                String imagenNombre = imagen.getOriginalFilename();
                Path imagenPath = Paths.get(UPLOAD_DIR + imagenNombre);
                Files.write(imagenPath, imagen.getBytes());

                // Establecer la URL de la imagen en la receta
                receta.setImagenUrl("/uploads/" + imagenNombre);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        recetaService.guardarReceta(receta);
        return "redirect:/recetas/list";
    }

    // Mostrar el formulario para editar una receta existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarReceta(@PathVariable("id") Long id, Model model) {
        Receta receta = recetaService.obtenerRecetaPorId(id);
        if (receta != null) {
            model.addAttribute("receta", receta);
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
            model.addAttribute("receta", receta);
            return "recetas/detalle"; // Página para mostrar los detalles de la receta
        } else {
            return "redirect:/recetas/list";
        }
    }
}
