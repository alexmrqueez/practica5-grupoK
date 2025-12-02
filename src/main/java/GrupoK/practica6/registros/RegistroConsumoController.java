package GrupoK.practica6.registros;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/registros")
public class RegistroConsumoController {

    private final RegistroConsumoServicio service;

    public RegistroConsumoController(RegistroConsumoServicio service) {
        this.service = service;
    }

    @GetMapping
    public String listarRegistros(Model model) {
        List<RegistroConsumo> lista = service.findAll();
        model.addAttribute("registros", lista);
        return "registros";   
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("registro", new RegistroConsumo());
        return "nuevo-registro";  
    }

    @PostMapping
    public String guardarRegistro(@ModelAttribute("registro") RegistroConsumo registro) {
        service.create(registro);
        return "redirect:/registros";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        RegistroConsumo registro = service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id no válido: " + id));

        model.addAttribute("registro", registro);
        return "editar-registro";  
    }

    @PostMapping("/editar/{id}")
    public String actualizarRegistro(@PathVariable Long id,
                                     @ModelAttribute("registro") RegistroConsumo registroActualizado) {

        service.update(id, registroActualizado);
        return "redirect:/registros";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarRegistro(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/registros";
    }
}
