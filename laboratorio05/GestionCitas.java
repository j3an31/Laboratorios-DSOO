package laboratorio05;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

class GestionCitas {
    // ArrayList para almacenar todas las citas
    private ArrayList<Cita> listaCitas;

    // Constructor
    public GestionCitas() {
        this.listaCitas = new ArrayList<>();
    }
    
    public ArrayList<Cita> getListaCitas() {
        return listaCitas;
    }

    // Método para crear y añadir una nueva cita, incluyendo la validación
    public boolean agregarCita(String codigoCita, String codigoDoctor, String codigoPaciente, LocalDate fecha, LocalTime hora, String estado) {
        if (existeCitaDuplicada(codigoDoctor, fecha, hora)) {
            System.out.println("ERROR: No se puede crear la cita. El Doctor " + codigoDoctor + 
            " ya tiene una cita programada el " + fecha + " a las " + hora);
            return false; 
        }
        Cita nuevaCita = new Cita(codigoCita, codigoDoctor, codigoPaciente, fecha, hora, estado);
        listaCitas.add(nuevaCita);
        System.out.println("Cita creada y agregada con éxito para el Doctor " + codigoDoctor);
        return true; 
    }

    // Verifica la duplicidad
    private boolean existeCitaDuplicada(String codigoDoctor, LocalDate fecha, LocalTime hora) {
        for (Cita cit : listaCitas) {
            // Se verifica si coincide el Doctor, la Fecha y la Hora
            if (cit.getCodigoDoctor().equals(codigoDoctor) &&
                cit.getFecha().equals(fecha) &&
                cit.getHora().equals(hora)) {
                return true; 
            }
        }
        return false; 
    }
    
    // Método para modificar Estado
    public boolean cambiarEstadoCita(String codigoCita, String nuevoEstado) {
        for (Cita cit : listaCitas) {
            if (cit.getCodigoCita().equals(codigoCita)) {
                String estadoAnterior = cit.getEstado();
                cit.setEstado(nuevoEstado);
                System.out.println("Estado de la cita " + codigoCita + " cambiado de " + estadoAnterior + " a " + cit.getEstado());
                return true;
            }
        }
        System.out.println("ERROR: No se encontró la cita con código " + codigoCita);
        return false;
    }

    public static void registrarCitas(Scanner sc, GestionCitas gestor, int numCitas) {
        sc.nextLine(); 
        
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        for (int i=0; i<numCitas; i++) {
            System.out.println("--- Cita N° " + (i+1) + " ---");
            System.out.print("Código de Cita (ej. C001): ");
            String codigoCita = sc.nextLine();
            
            System.out.print("Código de Doctor: ");
            String codigoDoctor = sc.nextLine();
            
            System.out.print("Código de Paciente: ");
            String codigoPaciente = sc.nextLine();
            
            LocalDate fecha = null;
            while (fecha == null) {
                System.out.print("Fecha (dd/MM/yyyy): ");
                String fechaStr = sc.nextLine();
                try {
                    fecha = LocalDate.parse(fechaStr, formatoFecha);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de fecha inválido. Use dd/MM/yyyy");
                }
            }

            LocalTime hora = null;
            while (hora == null) {
                System.out.print("Hora (HH:mm - 24h): ");
                String horaStr = sc.nextLine();
                try {
                    hora = LocalTime.parse(horaStr, formatoHora);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de hora inválido. Use HH:mm (ej. 09:30)");
                }
            }
            gestor.agregarCita(codigoCita, codigoDoctor, codigoPaciente, fecha, hora, "p");
        }
    }
    
    public static void mostrarCitasRegistradas(GestionCitas gestor) {
        if (gestor.getListaCitas().isEmpty()) {
            System.out.println("No hay citas registradas");
            return;
        }
        System.out.println("\n--- LISTADO DE CITAS REGISTRADAS ---");
        for (Cita cit : gestor.getListaCitas()) {
            System.out.println(cit.toString());
        }
        System.out.println("------------------------------------");
    }
    
    public static void gestionarEstadoCita(Scanner sc, GestionCitas gestor) {
        
        System.out.println("\n--- MODIFICAR ESTADO DE CITA ---");
        System.out.print("Ingrese el código de la Cita a modificar: ");
        String codigoCita = sc.next();
        
        System.out.print("Ingrese el nuevo estado (A: Atendida / C: Cancelada / P: Pendiente): ");
        String nuevoEstado = sc.next();
        
        gestor.cambiarEstadoCita(codigoCita, nuevoEstado);
    }
}