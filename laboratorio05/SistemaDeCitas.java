package laboratorio05;

import java.util.*;
public class SistemaDeCitas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("<<< SISTEMA DE REGISTRO - DOCTORES >>>");
        System.out.print("\n¿Cuantos doctores registrará?: ");
        int numDoctores = sc.nextInt();

        ArrayList<Doctor> arrayList_Doctores = new ArrayList<Doctor>();
        Doctor.registrarDoctor(sc, arrayList_Doctores, numDoctores);
        System.out.println("\n--- REGISTRO COMPLETADO ---");
        Doctor.mostrarDoctoresRegistrados(arrayList_Doctores);

        System.out.println("<<< SISTEMA DE REGISTRO - PACIENTES >>>");
        System.out.print("\n¿Cuantos pacientes registrará?: ");
        int numPacientes = sc.nextInt();

        ArrayList<Paciente> arrayList_Pacientes = new ArrayList<Paciente>();
        Paciente.registrarPaciente(sc, arrayList_Pacientes, numPacientes);
        System.out.println("\n--- REGISTRO COMPLETADO ---");
        Paciente.mostrarPacientesRegistrados(arrayList_Pacientes);
        

    sc.close();
    }
}

