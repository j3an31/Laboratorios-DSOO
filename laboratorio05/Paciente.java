package laboratorio05;

import java.util.ArrayList;
import java.util.Scanner;

class Paciente {

    int codigo_Paciente;
    String nombre_Paciente;
    int edad_Paciente;
    int dni_Paciente;
    
    Paciente(int cod, String nomb, int edad, int dni) {
        this.codigo_Paciente = cod;
        this.nombre_Paciente = nomb;
        this.edad_Paciente = edad;
        this.dni_Paciente = dni;
    }

    public int getEdad_Paciente() {
        return edad_Paciente;
    }

    public int getDni_Paciente() {
        return dni_Paciente;
    }

    public String toString() {
        return "Paciente [Código: " + codigo_Paciente + ", Nombre: " + nombre_Paciente + 
            ", Edad: " + edad_Paciente + ", DNI: " + dni_Paciente + "]";
    }

    public static void registrarPaciente(Scanner sc, ArrayList<Paciente> list_pacientes, int x_pacientes) {
        for(int i=1; i<=x_pacientes; i++) {
            System.out.println("--- Paciente N° " +i +" ---");

            System.out.print("Código: ");
            int cod_Ingresado = sc.nextInt();
            sc.nextLine();
            System.out.print("Nombres: ");
            String nombre_Ingresado = sc.nextLine();

            int edad_Ingresada;
            boolean validarEdad;
            do {
                System.out.print("Edad: ");
                edad_Ingresada = sc.nextInt();
                validarEdad = Paciente.edadMayor_A_0(edad_Ingresada);
                if (validarEdad == false) { 
                    System.out.println("¡ERROR! La edad es mejor a 0. Ingrese una edad diferente.");
                }
            } while (validarEdad == false);
            
            int dni_Ingresado;
            boolean validarDni;
            do {
                System.out.print("DNI: ");
                dni_Ingresado = sc.nextInt();
                validarDni = Paciente.dniRepetido(list_pacientes, dni_Ingresado); // Llamado a método para validar el código
                if (validarDni) {
                    System.out.println("¡ERROR! Este DNI ya fue registrado. Ingrese un DNI diferente.");
                }
            } while (validarDni); // Se repite el ciclo hasta que el DNI sea único
            list_pacientes.add(new Paciente(cod_Ingresado, nombre_Ingresado, edad_Ingresada, dni_Ingresado));
        }
    }

    public static boolean edadMayor_A_0(int x_edad) {
        if (x_edad > 0) {
            return true;
        }
        return false;
    }

    public static boolean dniRepetido(ArrayList<Paciente> list_pacientes, int dniNuevo) {
        for (Paciente pac : list_pacientes) { // For-ech que recorre toda la lista
            if (pac.getDni_Paciente() == dniNuevo) { 
                return true; 
            }
        }
        return false; 
    }

    public static void mostrarPacientesRegistrados(ArrayList<Paciente> list_pacientes ) {
        for (Paciente pac : list_pacientes) {
            System.out.println(pac.toString());
        }
    }
}
