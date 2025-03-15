package tech.Kabadev.presentacion;

import tech.Kabadev.Datos.EstudianteDAO;
import tech.Kabadev.Dominio.Estudiante;

import java.util.Scanner;

public class SystemaEstudiantesApp {
    public static void main(String[] args) {
        var salir = false;
        var consola = new Scanner(System.in);
        // Se crea una instancia de la clase servicio
        var estudianteDao = new EstudianteDAO();
        while (!salir) {
            try {
                mostrarMenu();
                salir = ejecutarOpciones(consola, estudianteDao);
            } catch (Exception e) {
                System.out.println("Ocurrió un error al ejecutar operación: " + e.getMessage());
            }
            System.out.println();
        } // fin while
    }

    public static void mostrarMenu() {
        System.out.println("""
                ****Sistema de estudiantes********
                1. Listar estudiantes
                2. Buscar estudiante
                3. Agregar Estudiante
                4. Modificar Estudiante
                5. Eliminar estudiante
                6. Salir
                """);
    }

    private static boolean ejecutarOpciones(Scanner consola, EstudianteDAO estudianteDAO) {
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion) {
            case 1 -> {
                System.out.println("Listado de estudiantes: ");
                var estudiantes = estudianteDAO.listarEstudiantes();
                estudiantes.forEach(System.out::println);
            }
            case 2 -> { // buscar por id
                System.out.println("Introduce el id_estudiante a buscar:");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante);
                if (encontrado) {
                    System.out.println("Estudiante encontrado: " + estudiante);
                } else {
                    System.out.println("Estudiante no encontrado: " + estudiante);
                }
            }
            case 3 -> {
                System.out.println("Agregar estudiante: ");
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Introduce el apellido");
                var apellido = consola.nextLine();
                System.out.println("Telefono: ");
                var telefono = consola.nextLine();
                System.out.println("Email: ");
                var email = consola.nextLine();
                // Crear objeto estudiante sin id
                var estudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado = estudianteDAO.agregarEstudiante(estudiante);
                if (agregado) {
                    System.out.println("Estudiante agregado: " + estudiante);
                } else {
                    System.out.println("Estudiante NO agregado: " + estudiante);
                }
            }
            case 4 -> { // Modificar estudiante
                System.out.println("Modificar estudiante: ");
                System.out.println("Id estudiante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Introduce el apellido");
                var apellido = consola.nextLine();
                System.out.println("Telefono: ");
                var telefono = consola.nextLine();
                System.out.println("Email: ");
                var email = consola.nextLine();
                // Crear el objeto estudiante a modificar
                var estudiante = new Estudiante(idEstudiante, nombre, apellido, telefono, email);
                var modificado = estudianteDAO.modificarEstudiante(estudiante);
                if (modificado) {
                    System.out.println("Estudiante modificado: " + estudiante);
                } else {
                    System.out.println("Estudiante no modificado: " + estudiante);
                }
            }
            case 5 -> { // Eliminar estudiante
                System.out.println("Eliminar estudiante: ");
                System.out.println("Id estudiante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var eliminado = estudianteDAO.eliminarEstudiante(estudiante);
                if (eliminado) {
                    System.out.println("Estudiante eliminado: " + estudiante);
                } else {
                    System.out.println("Estudiante NO eliminado: " + estudiante);
                }
            }
            case 6 -> {
                System.out.println("Saliendo del sistema...");
                salir = true;
            }
            default -> System.out.println("Opción no válida.");
        }
        return salir;
    }
}