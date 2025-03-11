CREATE TABLE 'estudiantes_DB'.'estudiante'(
    'id_estudiante' INT NOT NULL AUTO_INCREMENT,
    'nombre' VARCHAR(45) NULL,
    'apellido' VARCHAR(45)NULL,
    'telefono' VARCHAR(45) NULL,
    'email' varchar(45) NULL,
    PRIMARY KEY ('id_estudiante')
);

-- Listamos los estudiantes --
-- CRUD -- Create (Crear) - Read (Leer) - Update (Actualizar) - Delete (Borrar) --

SELECT * FROM estudiantes_DB.estudiante;
SELECT id_estudiante, nombre, apellido FROM estudiante;

--Create (Insert)--
INSERT INTO estudiante(nombre, apellido, telefono, email)VALUES
("Juan", "Perez", "12345678", "juan@mail.com";)

 --Update (Modificiar)--
 UPDATE estudiante SET nombre = "Juan Carlos", apellido = "Garcia" WHERE id_estudiante = 1;

 --Delete (Borrar)--
 DEFETE FROM estudiante WHERE id_estudiante = 1;

--Resetear el AUTO_INCREMENT-- FLUSH PRIVILEGES;
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'nueva_contraseña';
FLUSH PRIVILEGES;

FLUSH PRIVILEGES;
ALTER USER 'root'@'localhost' IDENTIFIED BY 'nueva_contraseña';