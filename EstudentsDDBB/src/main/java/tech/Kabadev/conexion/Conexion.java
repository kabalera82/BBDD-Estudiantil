package tech.Kabadev.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    //Creamos el metodo
    public static Connection getConexion(){
        Connection conexion = null;

        //Definimos las variables con las que nos vamos a conectar a las BBDD
        var baseDatos ="estudiantes_db";
        //VARIABLE DE DIRECCIÓN
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        //nombre usuario de la BBDD
        var usuario = "user";
        //contraseña de mysql;
        var password = "0000";
        // Cargamos la clase del driver de mysql en memoria
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return conexion;
    }

    //Creamos una clase de prueba de conexion
    public static void main(String[] args){
        //Variable de conexion.
        var conexion = Conexion.getConexion();
        if(conexion != null) {
            System.out.println("Conexion correcta:  " + conexion);
        }else {
            System.out.println("Error al conectarse.");
        }
    }
}
