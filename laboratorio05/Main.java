import java.time.*;

public class Main {
    public static void main(String[] args) {
        GestorCitas gestor = new GestorCitas();

        System.out.println("===== PRUEBA: REGISTRO DE DOCTORES =====");

        // Doctor válido
        Doctor d1 = new Doctor("D001", "Dr. Pérez", "Cardiología", 45, "08:00", "17:00");
        gestor.agregarDoctor(d1);

        // Doctor con código repetido
        Doctor d2 = new Doctor("D001", "Dra. Ramírez", "Neurología", 40, "09:00", "15:00");
        gestor.agregarDoctor(d2);

        // Doctor con horario inválido (entrada después de salida)
        Doctor d3 = new Doctor("D002", "Dr. García", "Traumatología", 50, "18:00", "12:00");
        gestor.agregarDoctor(d3);


        // Doctor válido adicional
        Doctor d5 = new Doctor("D004", "Dr. Morales", "Pediatría", 42, "09:00", "16:00");
        gestor.agregarDoctor(d5);

        System.out.println("\n===== PRUEBA: REGISTRO DE PACIENTES =====");

        // Paciente válido
        Paciente p1 = new Paciente("P001", "Juan López", 23, "DNI001");
        gestor.agregarPaciente(p1);

        // Paciente con DNI duplicado
        Paciente p2 = new Paciente("P002", "Ana Gómez", 12, "DNI001");
        gestor.agregarPaciente(p2);

        // Paciente con edad negativa
        Paciente p3 = new Paciente("P003", "Luis Pérez", -3, "DNI003");
        gestor.agregarPaciente(p3);

        // Paciente válido adicional
        Paciente p4 = new Paciente("P004", "Carla Rivera", 28, "DNI004");
        gestor.agregarPaciente(p4);

        System.out.println("\n===== PRUEBA: AGENDAR CITAS =====");

        // Cita válida dentro del horario del doctor
        Cita c1 = new Cita("C001", "D001", "P001", LocalDate.now().plusDays(1).toString(), "09:00");
        gestor.agregarCita(c1);

        // Cita en fecha pasada
        Cita c2 = new Cita("C002", "D001", "P001", LocalDate.now().minusDays(1).toString(), "10:00");
        gestor.agregarCita(c2);

        // Cita fuera del horario del doctor
        Cita c3 = new Cita("C003", "D001", "P001", LocalDate.now().plusDays(1).toString(), "18:00");
        gestor.agregarCita(c3);

        // Cita con hora ya ocupada (mismo doctor y hora)
        Cita c4 = new Cita("C004", "D001", "P004", LocalDate.now().plusDays(1).toString(), "09:00");
        gestor.agregarCita(c4);

        // Cita válida con otro doctor
        Cita c5 = new Cita("C005", "D004", "P004", LocalDate.now().plusDays(2).toString(), "10:30");
        gestor.agregarCita(c5);
    }
}
