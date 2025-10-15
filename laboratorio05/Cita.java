package laboratorio05;

import java.time.*;
public class Cita {
    private String codigoCita;
    private String codigoDoctor;
    private String codigoPaciente;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;

    // Constructor
    public Cita(String codigoCita, String codigoDoctor, String codigoPaciente, LocalDate fecha, LocalTime hora, String estado) {
        this.codigoCita = codigoCita;
        this.codigoDoctor = codigoDoctor;
        this.codigoPaciente = codigoPaciente;
        this.fecha = fecha;
        this.hora = hora;
        // Inicializa el estado de la cita, dependera de la eleccion del usuario [p, a, c]
        this.estado = convertirEstado(estado);
    }

    // Estado
    private String convertirEstado(String estado) {
        if (estado == null) {
            return "sincondición";
        }

        switch (estado.trim().toLowerCase()) { // Trim para eliminar espacios en blanco
            case "p": 
                return "PENDIENTE";
            case "a": 
                return "ATENDIDA";
            case "c": 
                return "CANCELADA";
            default:  
                return "sincondición";
        }
    }

    // Getters
    public String getCodigoCita() { return codigoCita; }
    public String getCodigoDoctor() { return codigoDoctor; }
    public String getCodigoPaciente() { return codigoPaciente; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public String getEstado() { return estado; }

    // Setters 
    public void setCodigoCita(String codigoCita) {this.codigoCita = codigoCita;}
    public void setCodigoDoctor(String codigoDoctor) {this.codigoDoctor = codigoDoctor;}
    public void setCodigoPaciente(String codigoPaciente) {this.codigoPaciente = codigoPaciente;}
    public void setFecha(LocalDate fecha) {this.fecha = fecha;}
    public void setHora(LocalTime hora) {this.hora = hora;}
    
    // Modifica el estado de la cita segun los criterios vistos
    public void setEstado(String estado) {
        this.estado = convertirEstado(estado);
    }   

    // ToString
    public String toString() {
        return "\nCITA: " + codigoCita +
               "\nFECHA: " + fecha +
               "\nHORA: " + hora +
               "\nPACIENTE: " + codigoPaciente +
               "\nDOCTOR: " + codigoDoctor +
               "\nESTADO: " + estado;
    }
}
