import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Doctor {
    private String codigo;
    private String nombre;
    private String especialidad;
    private int edad;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private ArrayList<Cita> citasAgendadas = new ArrayList<>();

    public Doctor(String codigo, String nombre, String especialidad, int edad, String horaEntrada, String horaSalida) {
        this.codigo=codigo;
        this.nombre=nombre;
        this.especialidad=especialidad;
        this.edad=edad;

        // Si la hora ingresada no corresponde al formato, lanza una excepción y no se crea el objeto  
        this.horaEntrada = validarHora(horaEntrada,"hora entrada"); 
        this.horaSalida = validarHora(horaSalida, "hora salida");
    }

    // Formato de hora HH:mm
    private DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

    // Metodo que valida el formato de la hora (HH:mm)
    private LocalTime validarHora(String hora, String tipoHora) {
        try {
            return LocalTime.parse(hora, FORMATO_HORA);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato inválido para la " + tipoHora + ". Debe estar en formato HH:mm, por ejemplo 09:30 o 17:00.");
        }
    }
    

    public String getCodigo() {return codigo;}
    public int getEdad() {return edad;}
    public String getNombre() {return nombre;}
    public String getEspecialidad() {return especialidad;}
    public LocalTime getHoraEntrada() {return horaEntrada;}
    public LocalTime getHoraSalida() {return horaSalida;}
    
    //Metodo que retorna las citas agendadas con el doctor
    public ArrayList<Cita> getCitasAgendadas() {return citasAgendadas;}

    public void agregarCita(Cita cita) {
        citasAgendadas.add(cita);
    }

    public String toString() {
        return "\nNOMBRE: "+nombre+
                "\nESPECIALIDAD: "+especialidad+
                "\nEDAD: "+ edad +
                "\nCODE: "+ codigo +
                "\nHORA: "+ horaEntrada +"-"+horaSalida;
    }
}
