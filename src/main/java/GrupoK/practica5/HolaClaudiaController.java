package GrupoK.practica5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaClaudiaController {

    @GetMapping("/claudia")
    public String hola() {
    	return "¡hello world desde claudia!" ;
    }

}