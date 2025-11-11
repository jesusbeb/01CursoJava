package org.example;

import org.example.contenido.Pelicula;
import org.example.plataforma.Usuario;


public class Main {
    //Constante (se nombra en mayuscula por convencion)
    public static final String NOMBRE_PLATAFORMA = "PLATZY PLAY ";
    public static final String VERSION = "1.0.0.";

    public static void main(String[] args) {
        System.out.println(NOMBRE_PLATAFORMA + VERSION);

        //Pelicula pelicula --> Declaracion, new Pelicula --> Instanciacion, (    ) --> Inicializacion
        Pelicula pelicula = new Pelicula("El señor de los anillos", 120, "Fantasia", 4.7);

        System.out.println(pelicula.obtenerFichaTecnica());

        Usuario usuario = new Usuario("Juan", "juan@platzi.com");
        usuario.ver(pelicula);
    }
}