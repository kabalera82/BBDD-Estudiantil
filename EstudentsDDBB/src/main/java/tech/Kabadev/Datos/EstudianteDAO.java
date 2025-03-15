package tech.Kabadev.Datos;

import tech.Kabadev.Dominio.Estudiante;

import static tech.Kabadev.conexion.Conexion.getConexion;
---+++
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// DAO Data Access Object
public class EstudianteDAO {
    // Vamos a probar que funciona
    public static void main(String[] args) {
        var estudianteDao = new EstudianteDAO();
        // Agregar estudiante
        var nuevoEstudiante = new Estudiante("Carlos", "Lara", "655222111", "carlosLar@gmail.com");
        var agregado = estudianteDao.agregarEstudiante(nuevoEstudiante);
        if (agregado) {
            System.out.println("Estudiante agregado: " + nuevoEstudiante);
        } else {
            System.out.println("No se pudo agregar el estudiante: " + nuevoEstudiante);
        }
        // Listar los estudiantes
        System.out.println("Listado de estudiantes: ");
        List<Estudiante> estudiantes = estudianteDao.listarEstudiantes();
        estudiantes.forEach(System.out::println);

        // Buscar por id el estudiante con id 1
        var estudiante1 = new Estudiante(1);
        System.out.println("Estudiante antes de la búsqueda: " + estudiante1);
        var encontrado = estudianteDao.buscarEstudiantePorId(estudiante1);
        if (encontrado) {
            System.out.println("Estudiante encontrado: " + estudiante1);
        } else {
            System.out.println("No se encontró estudiante con id: " + estudiante1.getIdEstudiante());
        }

        // Modificar un estudiante existente
        var estudianteModificar = new Estudiante(1, "Juan", "Perez", "12345678", "juan@gmail.com");
        var modificado = estudianteDao.modificarEstudiante(estudianteModificar);
        if (modificado) {
            System.out.println("Estudiante modificado: " + estudianteModificar);
        } else {
            System.out.println("No se modificó estudiante: " + estudianteModificar);
        }

        // Eliminar estudiante
        var estudianteEliminar = new Estudiante(3);
        var eliminado = estudianteDao.eliminarEstudiante(estudianteEliminar);
        if (eliminado) {
            System.out.println("Estudiante eliminado: " + estudianteEliminar);
        } else {
            System.out.println("No se eliminó el estudiante: " + estudianteEliminar);
            System.out.println("Listado de estudiantes: " + estudianteDao.listarEstudiantes());
        }
    }

    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        // Prepara la sentencia de SQL que vamos a ejecutar en la BBDD
        PreparedStatement ps;
        // Almacena el resultado obtenido de la BBDD
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            // Ciclo while para iterar todos los registros de la sentencia.
            while (rs.next()) {
                // Utilizamos el constructor vacío
                var estudiante = new Estudiante();
                // Utilizamos el método set para ir llenando sus valores cogiendo el registro id_estudiante
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                // Ahora hay que retornar la lista una vez cogida
                estudiantes.add(estudiante);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al seleccionar datos: " + e.getMessage());
        } finally {
            // Añadimos un bloque finally para asegurarnos de cerrar la conexión.
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrió un error al cerrar la conexión: " + e.getMessage());
            }
        }
        return estudiantes;
    }

    // Función para agregar un nuevo estudiante.
    // Generamos el método como un booleano dependiendo de si puede o no agregar un nuevo registro
    public boolean agregarEstudiante(Estudiante estudiante) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO estudiante(nombre, apellido, telefono, email) VALUES (?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            // Ejecutamos la sentencia por el método execute al ser una inserción solo execute
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar estudiante: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return false;
    }

    // Creamos un método para buscar un Estudiante por ID
    public boolean buscarEstudiantePorId(Estudiante estudiante) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if (rs.next()) {
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error en la búsqueda: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrió un error al cerrar conexión: " + e.getMessage());
            }
        }
        return false;
    }

    // Método de modificar estudiante
    public boolean modificarEstudiante(Estudiante estudiante) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE estudiante SET nombre=?, apellido=?, telefono=?, email=? WHERE id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante()); // Error corregido: Cambiado de setString a setInt
            ps.execute();
            return true; // Error corregido: Añadido retorno de true si la operación es exitosa
        } catch (Exception e) {
            System.out.println("Error al modificar estudiante: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean eliminarEstudiante(Estudiante estudiante) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar estudiante: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return false;
    }
}