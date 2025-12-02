package GrupoK.practica5;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AfricaController {

    @GetMapping("/africa")
    public String hola() {
        return "¡me gustan las patatas bravas!";
    }
}
