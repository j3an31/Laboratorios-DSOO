import java.time.*;
import java.util.*;



public class GestorCitas {
    private ArrayList<Cita> agendaCitas;
    private ArrayList<Paciente> listaPacientes;
    private ArrayList<Doctor> listaDoctores;

    public GestorCitas() {
        agendaCitas = new ArrayList<>();
        listaDoctores = new ArrayList<>();
        listaPacientes = new ArrayList<>();
    }
    
    // Metodo agregar Doctor a la Base
    public void agregarDoctor(Doctor doctor) {
        // Validaciones
        if (doctor==null) {
            System.out.println("Error: Doctor es nulo\n");
            return;
        }
        if (stringNoNuloVacio(doctor.getNombre()) || stringNoNuloVacio(doctor.getEspecialidad()) || stringNoNuloVacio(doctor.getCodigo())) {
            System.out.println("Error Doctor: Nombre, especialidad o codigo no debe ser vacio/nulo");
            return;
        }


        if (buscarDoctor(doctor.getCodigo())!=null) {
            System.out.println("Error Doctor: Codigo ya existente, no se hicieron cambios");
            return;
        }
        if (!edadValida(doctor.getEdad())) {
            System.out.println("Error Doctor: Edad invalida, no se hicieron cambios\n");
            return;
        }
    
        if (!horarioValido(doctor.getHoraEntrada(), doctor.getHoraSalida())) {
            System.out.println("Error Doctor: Los horarios no corresponden logica, no se hicieron cambios\n");
            return;
        }

        System.out.println("====== SISTEMA ======");
        System.out.println("Se agrego a: "+doctor+"\n");

        listaDoctores.add(doctor);
    }


    private Doctor buscarDoctor(String codigo) {
        for (Doctor doctor : listaDoctores) {
            if (doctor.getCodigo().equalsIgnoreCase(codigo)) {
                return doctor;
            }
        }
        return null;
    }
    
    private Cita buscarCita(String codigo) {
        for (Cita cita : agendaCitas) {
            if (cita.getCodigoCita().equalsIgnoreCase(codigo)) {
                return cita;
            }
        }
        return null;
    }
    
    

    // Metodo que evalua si un String esta vacio
    private boolean stringNoNuloVacio(String cadena){
        return cadena!=null && cadena.trim().isEmpty();
    }

    // Metodo Agregar Paciente a la Base
    public void agregarPaciente(Paciente paciente) {
        // Validaciones
        if (paciente==null) {
            System.out.println("Error: Paciente nulo, no se hicieron cambios\n");
            return;
        }
        
        if (stringNoNuloVacio(paciente.getNombre()) || stringNoNuloVacio(paciente.getDNI()) || stringNoNuloVacio(paciente.getCodigo())) {
            System.out.println("Error: Nombre, DNI, CODIGO no deben ser nulos o vacios\n");
            return;
        }

        if (buscarPacienteDNI(paciente.getDNI())!=null) {
            System.out.println("Error: DNI ya registrado, no se hicieron cambios\n");
            return;
        }
        
        if (buscarPacienteCodigo(paciente.getCodigo())!=null) {
            System.out.println("Error: Codigo ya existe, no se hicieron cambios\n");
            return;
        }

        if (!edadValida(paciente.getEdad())) {
            System.out.println("Error: Edad invalida, no se hicieron cambios\n");
            return;
        }
        
        System.out.println("====== SISTEMA ======");
        System.out.println("Se agrego Paciente:"+paciente+"\n");
        listaPacientes.add(paciente);
    }


    // Metodo que verifica si la edad es valida
    private boolean edadValida(int edad) {
        if (edad<0) {
            return false;
        }
        return true;
    }
    
    // Metodos de busqueda de Paciente en la lista
    private Paciente buscarPacienteCodigo(String codigo) {
        for (Paciente paciente : listaPacientes) {
            if (paciente.getCodigo().equalsIgnoreCase(codigo)) {
                return paciente;
            }
        }
        return null;
    }

    private Paciente buscarPacienteDNI(String dni) {
        for (Paciente paciente : listaPacientes) {
            if (paciente.getDNI().equalsIgnoreCase(dni)) {
                return paciente;
            }
        }
        return null;
    }

    // Metodo que evalua el correcto formato de un horario (Hora de entrada - Hora de salida)
    private boolean horarioValido (LocalTime horaE, LocalTime horaS) {
        if (horaE.isAfter(horaS)) {
            return false;
        }
        return true;
    }

    public void agregarCita(Cita cita) {
        if (cita==null) {
            System.out.println("Error: Cita nula, no se hicieron cambios\n");
            return;   
        }
        
        Doctor doctorAsignado = buscarDoctor(cita.getCodigoDoctor());
        Paciente paciente = buscarPacienteCodigo(cita.getCodigoPaciente());
        
        if (doctorAsignado==null) {
            System.out.println("Error: Doctor asignado no registrado, no se hicieron cambios\n");
            return;
        }
        
        ArrayList<Cita> citasDoctor = doctorAsignado.getCitasAgendadas();

        if (paciente==null) {
            System.out.println("Error: Paciente no registrado, no se hicieron cambios\n");
            return;
        }

        // Validaciones para fecha y hora en la cita
        if (cita.getFecha()==null || cita.getHora()==null) {
            System.out.println("Error: Fecha u hora nula, no se hicieron cambios\n");
            return;
        }
        if (cita.getFecha().isBefore(LocalDate.now())) {
            System.out.println("Error: Fecha de cita en el pasado, no se hicieron cambios\n");
            return;
        }
        if (!citaDentroDeHorario(doctorAsignado, cita.getHora())) {
            System.out.println("Error: Hora de cita fuera del horario de atencion del doctor, no se hicieron cambios\n");   
            return;
        }


        if (buscarCita(cita.getCodigoCita())!=null) {
            System.out.println("Codigo de Cita ya registrado, no se hicieron cambios\n");
        }
        
        if (!horarioLibre(citasDoctor, cita.getHora())) {
            System.out.println(">>>>>> Horario ocupado, cita ya programada para las "+cita.getHora()+" con el Dr. "+doctorAsignado.getNombre()+"<<<<<<<<\n");
            return;
        }

        agendaCitas.add(cita);
        doctorAsignado.agregarCita(cita);
        System.out.println("====== SISTEMA ======");
        System.out.println("Cita programada: "+cita);
    }

    // Metodo que verifica si la hora de la cita esta dentro del horario del doctor
    private boolean citaDentroDeHorario(Doctor doctor, LocalTime hora) {
        return !hora.isBefore(doctor.getHoraEntrada()) && !hora.isAfter(doctor.getHoraSalida());
    }

    // Metodo que verifica si el horario de la cita esta libre
    private boolean horarioLibre(ArrayList<Cita> citasDoctor, LocalTime hora) {
        for (Cita cita : citasDoctor) {
            if (cita.getHora().equals(hora)) {
                return false;
            }
        }
        return true;
    }
    
}
