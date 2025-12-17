package GrupoK.practica6.registros;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroConsumoServicio {

    private final RegistroConsumoRepositorio repository;

    public RegistroConsumoServicio(RegistroConsumoRepositorio repository) {
        this.repository = repository;
    }

    public List<RegistroConsumo> findAll() {
        return repository.findAll();
    }

    public Optional<RegistroConsumo> findById(Long id) {
        return repository.findById(id);
    }

    public RegistroConsumo create(RegistroConsumo registro) {
        if (registro.getFechaHora() == null) {
            registro.setFechaHora(LocalDateTime.now());
        }
        return repository.save(registro);
    }

    public Optional<RegistroConsumo> update(Long id, RegistroConsumo datos) {
        return repository.findById(id).map(existente -> {
            existente.setMililitros(datos.getMililitros());
            existente.setFechaHora(datos.getFechaHora());
            return repository.save(existente);
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
