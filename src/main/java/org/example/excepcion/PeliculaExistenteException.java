package org.example.excepcion;

//Creamos una Exception personalizada
//RuntimeException permite representar errores en tiempo de ejecucion
public class PeliculaExistenteException extends RuntimeException{

    //Constructor que recibe un mensaje
    public PeliculaExistenteException(String titulo){
        super("El contenido " + titulo + " ya axiste.");
    }
}



/*
En Java, existen dos grandes tipos de excepciones:

Checked exceptions: Java obliga a controlarlas explícitamente usando try-catch. Ejemplos incluyen
IO Exception, FileNotFoundException y ParseException, las cuales pueden ocurrir al leer archivos inexistentes o
al interpretar datos incorrectos.

Unchecked exceptions: No es obligatorio manejarlas, pero pueden causar problemas en tiempo de ejecución si
las ignoramos. Algunos ejemplos son NullPointerException, NumberFormatException, IndexOutOfBoundsException y
la conocida IllegalArgumentException.
*/