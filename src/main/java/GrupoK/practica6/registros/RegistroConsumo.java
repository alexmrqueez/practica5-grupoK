package GrupoK.practica6.registros;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RegistroConsumo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int mililitros;   
    private LocalDateTime fechaHora; 

    public RegistroConsumo() {
    }

    public RegistroConsumo(int mililitros, LocalDateTime fechaHora) {
        this.mililitros = mililitros;
        this.fechaHora = fechaHora;
    }

    public Long getId() {
        return id;
    }

    public int getMililitros() {
        return mililitros;
    }

    public void setMililitros(int mililitros) {
        this.mililitros = mililitros;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}

