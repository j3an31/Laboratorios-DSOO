package laboratorio05;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

class Doctor {

    int codigo_Doctor;
    String nombre_Doctor;
    String especialidad_Doctor;
    LocalTime horaInicio; // Se utiliza LocalTime para almacenar el horario de atención
    LocalTime horaFin;

    Doctor(int cod, String nomb, String espec, LocalTime star, LocalTime end) {
        this.codigo_Doctor = cod;
        this.nombre_Doctor = nomb;
        this.especialidad_Doctor = espec;
        this.horaInicio = star;
        this.horaFin = end; 
    }

    public int getCodigo_Doctor() {
        return codigo_Doctor;
    }

    public String getNombre_Doctor() {
        return nombre_Doctor;
    }

    public String toString() {
        return "Doctor [Código: " + codigo_Doctor + ", Nombre: " + nombre_Doctor + 
            ", Especialidad: " + especialidad_Doctor + ", Horario: " + horaInicio + " a " + horaFin + "]";
    }

    public static void registrarDoctor(Scanner sc, ArrayList<Doctor> list_doctores, int x_doctores) {
        for(int i=1; i<=x_doctores; i++) {
            System.out.println("--- Doctor N° " +i +" ---");
            boolean validarCodigo;
            int cod_Ingresado = 0;

            do {
                System.out.print("Código: ");
                cod_Ingresado = sc.nextInt();
                sc.nextLine();
                validarCodigo = Doctor.CodigoRepetido(list_doctores, cod_Ingresado); // Llamado a método para validar el código
                if (validarCodigo) { 
                    System.out.println("¡ERROR! Este código ya existe. Ingrese un código diferente.");
                }
            } while (validarCodigo); // Se repite el ciclo hasta que el código sea único
              
            try { // Utilizamos manejo de excepciones para horas
                System.out.print("Nombres: ");
                String nombre_Ingresado = sc.nextLine();
                System.out.print("Especialidad: " );
                String especialidad = sc.nextLine();
                System.out.print("Horario de atención (INICIO - ej. 08:00): ");
                String horaInicio = sc.next();
                LocalTime start_Ingresado = LocalTime.parse(horaInicio); // Convierte String ingresado en un objeto de tipo LocalTime
                System.out.print("Horario de atención (CIERRE - ej. 14:00): "); // para manipular, comparar y realizar cálculos con la hora
                String horaCierre = sc.next();
                LocalTime end_Ingresado = LocalTime.parse(horaCierre); 
        
                list_doctores.add(new Doctor(cod_Ingresado, nombre_Ingresado, especialidad, start_Ingresado, end_Ingresado));
            
            } catch (DateTimeParseException e) {
                // Manejo de error si la hora es incorrecta
                System.out.println("¡ERROR! Formato de hora inválido. Intente de nuevo.");
                i--; // Decrementamos i para que el bucle for intente registrar el mismo doctor de nuevo
                sc.nextLine(); 
            }
        }
    }

    public static boolean CodigoRepetido(ArrayList<Doctor> list_doctores, int codigoNuevo) {
        for (Doctor doc : list_doctores) { // For-ech que recorre toda la lista
            if (doc.getCodigo_Doctor() == codigoNuevo) { 
                return true; 
            }
        }
        return false; 
    }

    public static void mostrarDoctoresRegistrados(ArrayList<Doctor> list_doctores ) {
        for (Doctor doc : list_doctores) {
            System.out.println(doc.toString());
        }
    }
}


