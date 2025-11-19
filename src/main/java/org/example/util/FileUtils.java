package org.example.util;

import org.example.contenido.Genero;
import org.example.contenido.Pelicula;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static final String ARCHIVO_TXT = "peliculas.txt"; //constante con la ruta y/o nombre del archivo
    public static final String SEPARADOR = "|";


    //Metodo para inicializar una List<Pelicula< desde un archivo txt
    public static List<Pelicula> leerArchivo(){
        //Lista de Peliculas para almacenar las que se obtendran del archivo
        List<Pelicula> peliculasDesdeArchivo = new ArrayList<>();

        try{
            //Leemos las lineas del archivo y las almacenamos en un List<String>
            //En cada linea del archivo, esta la informacion de cada Pelicula
            List<String> lineas = Files.readAllLines(Paths.get(ARCHIVO_TXT));

            //Recorremos cada linea y separamos cada atributo, usando el metodo split e
            //indicando el caracter que separa cada atributo "|". Guardamos en un arreglo de tipo String
            lineas.forEach(linea -> {
                String[] atributos = linea.split("\\" + SEPARADOR);

                //Cada linea debe tener 5 atributos para extraer cada uno a su tipo de dato
                //Para la calificacion, usamos isBlank y preguntamos si viene vacia y de ser asi
                //ponemos 0 por default. Si usaramos isEmpty() no consideraria espacios vacios como isBlank
                if (atributos.length == 5){
                    String titulo = atributos[0];
                    int duracion = Integer.parseInt(atributos[1]);
                    Genero genero = Genero.valueOf(atributos[2].toUpperCase());
                    double calificacion = atributos[3].isBlank() ? 0 : Double.parseDouble(atributos[3]);
                    LocalDate fechaEstreno = LocalDate.parse(atributos[4]);

                    Pelicula pelicula = new Pelicula(titulo, duracion, genero, calificacion); //Instanciamos e inicializamos
                    pelicula.setFechaEstreno(fechaEstreno);

                    peliculasDesdeArchivo.add(pelicula); //agregamos la pelicula al List
                }
            });

        } catch (IOException e){ //Capturamos dentro de la variable "e" el IOException en caso de error al leer el archivo
            System.out.println("Error leyendo el archivo " +e.getMessage()); //Imprimimos un mensaje, junto con el mensaje del IOException
        }
        return peliculasDesdeArchivo;
    }
}
