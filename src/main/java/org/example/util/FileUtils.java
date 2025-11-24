package org.example.util;

import org.example.contenido.Genero;
import org.example.contenido.Contenido;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static final String ARCHIVO_TXT = "contenido.txt"; //constante con la ruta y/o nombre del archivo
    public static final String SEPARADOR = "|";


    //Metodo para agregar un objeto Contenido a una linea de texto en un archivo txt
    public static void escribirContenido(Contenido contenido){
        //Convertimos el objeto en una linea, usando el metodo join, al cual le indicamos
        //el caracter separador para cada atributo y enviamos los atributos en String
        String linea = String.join(SEPARADOR,
                contenido.getTitulo(),
                String.valueOf( contenido.getDuracion() ),
                contenido.getGenero().name(),
                String.valueOf( contenido.getCalificacion() ),
                contenido.getFechaEstreno().toString()
        );

        //*El archivo txt debe tener una linea vacia al final del ultimo contenido existente
        try{
            Files.writeString(Paths.get(ARCHIVO_TXT), //Indicamos la ruta del archivo a escribir
                    linea + System.lineSeparator(), //Agregamos la linea que se escribira y con System.lineaSeparator concatenamos un enter
                    StandardOpenOption.CREATE, //Si el archivo no existe, se crea
                    StandardOpenOption.APPEND); //Se agrega la linea sin reemplazar lo existente
        } catch (IOException e){
            System.out.println("Error escribiendo el archivo " +e.getMessage());
        }
    }


    //Metodo para inicializar una List<Contenido> desde un archivo txt
    public static List<Contenido> leerArchivo(){
        //Lista de Contenido para almacenar lo que se obtendran del archivo
        List<Contenido> contenidoDesdeArchivo = new ArrayList<>();

        try{
            //Leemos las lineas del archivo y las almacenamos en un List<String>
            //En cada linea del archivo, esta la informacion de cada Contenido
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

                    Contenido contenido = new Contenido(titulo, duracion, genero, calificacion); //Instanciamos e inicializamos
                    contenido.setFechaEstreno(fechaEstreno);

                    contenidoDesdeArchivo.add(contenido); //agregamos el Contenido al List
                }
            });

        } catch (IOException e){ //Capturamos dentro de la variable "e" el IOException en caso de error al leer el archivo
            System.out.println("Error leyendo el archivo " +e.getMessage()); //Imprimimos un mensaje, junto con el mensaje del IOException
        }
        return contenidoDesdeArchivo;
    }
}
