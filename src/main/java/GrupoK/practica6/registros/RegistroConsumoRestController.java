package GrupoK.practica6.registros;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/registros")
public class RegistroConsumoRestController {

    private final RegistroConsumoServicio service;

    public RegistroConsumoRestController(RegistroConsumoServicio service) {
        this.service = service;
    }

    
    @GetMapping
    public List<RegistroConsumo> getAll() {
        return service.findAll();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<RegistroConsumo> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<RegistroConsumo> create(@RequestBody RegistroConsumo registro) {
        RegistroConsumo creado = service.create(registro);
        return ResponseEntity.ok(creado);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<RegistroConsumo> update(
            @PathVariable Long id,
            @RequestBody RegistroConsumo registro) {

        return service.update(id, registro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
