package tech.Kabadev.Dominio;

public class Estudiante {
    //Agregamos los atributos de nuestra BBDD
    private int idEstudiante;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    //tambien necesitamos generar los contructores.
    //Necesitamos varios de tipos de contructores para las diferente necesidades.

    //constructor vacio.
    public Estudiante() {
    }

    //Constructor con unicamente el id para busquedas por id.
    public Estudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    //Contructor con todos los atributos salvo la PK porque es AUTOINCREMENT
    public Estudiante(String nombre, String apellido, String telefono, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    //Constructor con todos los atributos
    public Estudiante(int idEstudiante, String nombre, String apellido, String telefono, String email) {
        this.idEstudiante = idEstudiante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    //Metodos Get y Set

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "idEstudiante=" + idEstudiante +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
