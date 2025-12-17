package GrupoK.practica6.registros;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RegistroConsumoTest {

    @Test
    @DisplayName("Entidad: constructor vacío y setters")
    void constructorVacio_y_setters() {
        RegistroConsumo registro = new RegistroConsumo();
        LocalDateTime fecha = LocalDateTime.now();

        registro.setMililitros(300);
        registro.setFechaHora(fecha);

        assertEquals(300, registro.getMililitros());
        assertEquals(fecha, registro.getFechaHora());
    }

    @Test
    @DisplayName("Entidad: constructor con parámetros")
    void constructorConParametros() {
        LocalDateTime fecha = LocalDateTime.of(2025, 1, 1, 10, 0);

        RegistroConsumo registro = new RegistroConsumo(500, fecha);

        assertEquals(500, registro.getMililitros());
        assertEquals(fecha, registro.getFechaHora());
        assertNull(registro.getId(), "El id debe ser null antes de persistir");
    }
}
