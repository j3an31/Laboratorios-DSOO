public class Paciente {
    private String codigo;
    private String nombre;
    private int edad;
    private String DNI;

    public Paciente(String codigo, String nombre, int edad, String DNI) {
        this.codigo=codigo;
        this.nombre=nombre;
        this.edad=edad;
        this.DNI=DNI;
    }

    public String getCodigo() {return codigo;}
    public String getDNI() {return DNI;}
    public int getEdad() {return edad;}
    public String getNombre() {return nombre;}

    public String toString() {
        return "\nNOMBRE: "+nombre+
                "\nEDAD: "+edad+
                "\nDNI: "+DNI+
                "\nCODE: "+codigo;
    }
}
