package GrupoK.practica5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlexController {

    @GetMapping("/alex")
    public String saludo() {
        return "¡Hola Mundo pero cambiado!";
    }
}

