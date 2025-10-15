package laboratorio05;

import java.util.ArrayList;
import java.util.Scanner;

public class Cita {
    int codigoCita;
    Paciente paciente;
    Doctor doctor;
    String fecha;
    String hora;
    String estado;

    Cita(int cod, Paciente pac, Doctor doc, String fech, String hor) {
        this.codigoCita = cod;
        this.paciente = pac;
        this.doctor = doc;
        this.fecha = fech;
        this.hora = hor;
        this.estado = "pendiente"; // estado por defecto
    }

    public int getCodigoCita(){
        return codigoCita;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getFecha(){
        return fecha;
    }

    public String getHora(){
        return hora;
    }

    public void setEstado(String nuevoEstado) {
        if (nuevoEstado.equalsIgnoreCase("pendiente") ||
            nuevoEstado.equalsIgnoreCase("atendida") ||
            nuevoEstado.equalsIgnoreCase("cancelada")) {
            this.estado = nuevoEstado.toLowerCase();
        } else {
            System.out.println("Estado inválido. Debe ser: pendiente, atendida o cancelada");
        }
    }

    public String toString() {
        return "Cita [Código: " + codigoCita + 
                ", Paciente: " + paciente.nombre_Paciente + 
                ", Doctor: " + doctor.nombre_Doctor + 
                ", Fecha: " + fecha + 
                ", Hora: " + hora + 
                ", Estado: " + estado + "]";
    }

    public static Paciente seleccionarPaciente(Scanner sc, ArrayList<Paciente> pacientes){
        System.out.println("\n--- LISTA DE PACIENTES ---");
        for (Paciente p: pacientes){
            System.out.println(p.getCodigoPaciente() + " : " + p.getNombre_Paciente());
        }

        System.out.print("Ingrese el codigo del paciente: ");
        int codBuscado = sc.nextInt();
        
        for (Paciente p: pacientes){
            if (p.getCodigoPaciente() == codBuscado){
                return p;
            }
        }

        System.out.println("Paciente no encontrado");
        return null;
    }

    public static Doctor seleccionarDoctor(Scanner sc, ArrayList <Doctor> list_doctores){
        System.out.println("\n--- LISTA DE DOCTORES ---");
        for(Doctor d: list_doctores){
            System.out.println(d.getCodigo_Doctor() + " : " + d.getNombre_Doctor());
        }

        System.out.println("Ingrese el codigo del doctor: ");
        int codBuscado = sc.nextInt();

        for(Doctor d: list_doctores){
            if(d.getCodigo_Doctor() == codBuscado){
                return d;
            }
        }

        System.out.println("Doctor no encontrado");
        return null;
    }

    public static boolean verificarConflicto(ArrayList<Cita> list_citas, Doctor doctor, String fecha, String hora) {
        for (Cita c: list_citas){
            if (c.getDoctor().getCodigo_Doctor() == doctor.getCodigo_Doctor() &&
                c.getFecha().equals(fecha) &&
                c.getHora().equals(hora)) {
                return true;
            }
        }
        return false;
    }

    public static void registrarCita(Scanner sc, ArrayList<Cita> list_citas, ArrayList<Paciente> list_pacientes, ArrayList<Doctor> list_doctores) {
        System.out.println("\n Codigo de la cita");
        int codigoCita = sc.nextInt();
        sc.nextLine(); 

        Paciente pac = seleccionarPaciente(sc, list_pacientes);
        if (pac == null) return;

        Doctor doc = seleccionarDoctor(sc, list_doctores);
        if(doc == null) return;

        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = sc.nextLine();
        System.out.print("Hora (HH:MM): ");
        String hora = sc.nextLine();

        if (verificarConflicto(list_citas, doc, fecha, hora)) {
            System.out.println("El doctor ya tiene una cita en esa fecha y hora.");
            return;
        }

        list_citas.add(new Cita(codigoCita, pac, doc, fecha, hora));
        System.out.println("Cita registrada");
    }

    public static void mostrarCitas (ArrayList<Cita> list_citas) {
        if (list_citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
        } else {
            System.out.println("\n--- LISTA DE CITAS REGISTRADAS ---");
            for (Cita cita : list_citas) {
                System.out.println(cita);
            }
        }
    }

    public static void cambiarEstadoCita(Scanner sc, ArrayList<Cita> citas) {
        mostrarCitas(citas);

        System.out.print("Ingrese el código de la cita a modificar: ");
        int cod = sc.nextInt();
        sc.nextLine();

        for (Cita c : citas) {
            if (c.getCodigoCita() == cod) {
                System.out.print("Nuevo estado (pendiente / atendida / cancelada): ");
                String nuevoEstado = sc.nextLine();
                c.setEstado(nuevoEstado);
                System.out.println("Estado actualizado.");
                return;
            }
        }

        System.out.println("¡Cita no encontrada!");
    }
}


