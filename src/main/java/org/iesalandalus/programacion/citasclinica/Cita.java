package org.iesalandalus.programacion.citasclinica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cita {

    // Constante
    public static final String FORMATO_FECHA_HORA = "dd/MM/yyyy HH:mm";

    // Atributos
    private LocalDateTime fechaHora;
    private Paciente paciente;

    //Constructores
    public Cita(Paciente paciente, LocalDateTime fechaHora) {
        setPaciente(paciente);
        setFehcaHora(fechaHora);
    }

    public Cita(Paciente p) {
        if (p != null) {
            setPaciente(paciente);
            setFehcaHora(fechaHora);
        } else {
            throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
        }
    }

    // Métodos
    private void setPaciente(Paciente p) {
        if (p != null) {
            this.paciente = new Paciente(p);
        } else {
            throw new NullPointerException("ERROR: El paciente de una cita no puede ser nulo.");
        }
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setFehcaHora(LocalDateTime fechaHora) {
        if (fechaHora != null) {
            this.fechaHora = fechaHora;
        } else {
            throw new NullPointerException("ERROR: La fecha y hora de una cita no puede ser nula.");
        }
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaHora);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cita other = (Cita) obj;
        return Objects.equals(fechaHora, other.fechaHora);
    }

    @Override
    public String toString() {
        return "fechaHora= " + fechaHora.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA)) + " " + paciente;
    }
}
