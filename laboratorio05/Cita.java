import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Cita {
    private String codigoCita;
    private String codigoDoctor;
    private String codigoPaciente;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;

    // Constructor
    public Cita(String codigoCita, String codigoDoctor, String codigoPaciente, String fecha, String hora) {
        this.codigoCita = codigoCita;
        this.codigoDoctor = codigoDoctor;
        this.codigoPaciente = codigoPaciente;
        
        // Si la fecha u hora ingresada no corresponde al formato, lanza una excepción y no se crea el objeto
        this.fecha = validarFecha(fecha);
        this.hora = validarHora(hora);

        // Estado inicial de la cita es "pendiente"
        this.estado = "pendiente";
    }

    // Formatos de fecha y hora
    private DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
    private DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Métodos de validación para fecha y hora
    private LocalTime validarHora(String hora) {
        try {
            return LocalTime.parse(hora, FORMATO_HORA);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato inválido para la HORA, debe estar en formato HH:mm, por ejemplo 09:30 o 17:00.");
        }
    }

    private LocalDate validarFecha(String fecha) {
        try {
            return LocalDate.parse(fecha, FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato inválido para FECHA, debe estar en formato dd/MM/yyyy, por ejemplo 25/12/2023.");
        }
    }


    // Metodo para cambiar el estado de la cita
    public String cambiarEstado(String estado) {
        if (estado == null) return "sin condición";
        switch (estado.trim().toLowerCase()) {
            case "p": return "pendiente";
            case "a": return "atendida";
            case "c": return "cancelada";
            default:  return "sin condición";
        }
    }

    // GETTERS 
    public String getCodigoCita() { return codigoCita; }
    public String getCodigoDoctor() { return codigoDoctor; }
    public String getCodigoPaciente() { return codigoPaciente; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public String getEstado() { return estado; }

    // TO STRING
    public String toString() {
        return "\nCITA: " + codigoCita +
               "\nFECHA: " + fecha +
               "\nHORA: " + hora +
               "\nPACIENTE: " + codigoPaciente +
               "\nDOCTOR: " + codigoDoctor +
               "\nESTADO: " + estado;
    }
}

