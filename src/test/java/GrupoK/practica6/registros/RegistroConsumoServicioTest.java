package GrupoK.practica6.registros;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistroConsumoServicioTest {

    @Mock
    private RegistroConsumoRepositorio repositorio;

    @InjectMocks
    private RegistroConsumoServicio servicio;

    @Test
    @DisplayName("AAA: findAll devuelve lista del repositorio")
    void findAll_devuelveLista() {
        when(repositorio.findAll())
                .thenReturn(List.of(new RegistroConsumo(), new RegistroConsumo()));

        List<RegistroConsumo> resultado = servicio.findAll();

        assertEquals(2, resultado.size());
        verify(repositorio).findAll();
    }

    @Test
    @DisplayName("AAA: findById devuelve registro cuando existe")
    void findById_existe() {
        RegistroConsumo registro = new RegistroConsumo();
        when(repositorio.findById(1L)).thenReturn(Optional.of(registro));

        Optional<RegistroConsumo> resultado = servicio.findById(1L);

        assertTrue(resultado.isPresent());
        verify(repositorio).findById(1L);
    }

    @Test
    @DisplayName("AAA: create asigna fechaHora si es null")
    void create_fechaHoraNull() {
        RegistroConsumo registro = new RegistroConsumo();
        registro.setMililitros(250);
        registro.setFechaHora(null);

        when(repositorio.save(any()))
                .thenAnswer(inv -> inv.getArgument(0));

        RegistroConsumo creado = servicio.create(registro);

        assertNotNull(creado.getFechaHora());
        verify(repositorio).save(registro);
    }

    @Test
    @DisplayName("AAA: create NO cambia fechaHora si ya viene informada")
    void create_fechaHoraNoNull() {
        LocalDateTime fecha = LocalDateTime.of(2025, 1, 1, 12, 0);

        RegistroConsumo registro = new RegistroConsumo();
        registro.setMililitros(100);
        registro.setFechaHora(fecha);

        when(repositorio.save(any()))
                .thenAnswer(inv -> inv.getArgument(0));

        RegistroConsumo creado = servicio.create(registro);

        assertEquals(fecha, creado.getFechaHora());
        verify(repositorio).save(registro);
    }

    @Test
    @DisplayName("AAA: update actualiza cuando el id existe")
    void update_existe() {
        RegistroConsumo existente = new RegistroConsumo();
        existente.setMililitros(100);
        existente.setFechaHora(LocalDateTime.now());

        RegistroConsumo nuevosDatos = new RegistroConsumo();
        nuevosDatos.setMililitros(400);
        nuevosDatos.setFechaHora(LocalDateTime.now().plusHours(1));

        when(repositorio.findById(1L)).thenReturn(Optional.of(existente));
        when(repositorio.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Optional<RegistroConsumo> resultado = servicio.update(1L, nuevosDatos);

        assertTrue(resultado.isPresent());
        assertEquals(400, resultado.get().getMililitros());
        verify(repositorio).save(existente);
    }

    @Test
    @DisplayName("AAA: update devuelve Optional.empty si no existe")
    void update_noExiste() {
        when(repositorio.findById(99L)).thenReturn(Optional.empty());

        Optional<RegistroConsumo> resultado = servicio.update(99L, new RegistroConsumo());

        assertTrue(resultado.isEmpty());
        verify(repositorio, never()).save(any());
    }

    @Test
    @DisplayName("AAA: delete llama a deleteById del repositorio")
    void delete_llamaRepositorio() {
        servicio.delete(5L);

        verify(repositorio).deleteById(5L);
    }
}
