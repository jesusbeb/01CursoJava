package org.example.util;

import org.example.contenido.Documental;
import org.example.contenido.Genero;
import org.example.contenido.Contenido;
import org.example.contenido.Pelicula;

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

        String lineaFinal;

        //Con instanceof verificamos si el contenido recibido es instancia de Documental; si lo es,
        //dentro del if se crea automaticamente una variable llamada documental ya casteada al tipo Documental
        //(Esto es una caracteristica de Java, llamada pattern matching), despues
        //agregamos al principio de la linea de texto, "DOCUMENTAL" y al final agregamos el narrador
        //en otro caso, solo agregamos "PELICULA" y "|"
        if (contenido instanceof Documental documental){
            lineaFinal = "DOCUMENTAL" + SEPARADOR + linea + SEPARADOR + documental.getNarrador();
        } else {
            lineaFinal = "PELICULA" + SEPARADOR + linea;
        }

        //* El archivo txt debe tener una linea vacia al final del ultimo contenido existente
        //** Posteriormente se agrego un nuevo campo al principio de cada linea del archivo txt: "DOCUMENTAL" o "PELICULA"
        //*** En el caso de Documental, se agrego un sexto campo para el narrador
        try{
            Files.writeString(Paths.get(ARCHIVO_TXT), //Indicamos la ruta del archivo a escribir
                    lineaFinal + System.lineSeparator(), //Agregamos la linea que se escribira y con System.lineaSeparator concatenamos un enter
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

                //Identificamos el atributo con el tipo de contenido
                String tipoContenido = atributos[0];

                //Cada linea tendra diferente numero de atributos en cierto orden para extraer cada uno a su tipo de dato
                //Verificamos que Pelicula tenga 6 atributos o Documental 7 atributos
                //Para la calificacion, usamos isBlank y preguntamos si viene vacia y de ser asi
                //ponemos 0 por default. Si usaramos isEmpty() no consideraria espacios vacios como isBlank
                if ( ("PELICULA".equals(tipoContenido) && atributos.length == 6)
                        || ("DOCUMENTAL".equals(tipoContenido) && atributos.length == 7) ){
                    String titulo = atributos[1];
                    int duracion = Integer.parseInt(atributos[2]);
                    Genero genero = Genero.valueOf(atributos[3].toUpperCase());
                    double calificacion = atributos[4].isBlank() ? 0 : Double.parseDouble(atributos[4]);
                    LocalDate fechaEstreno = LocalDate.parse(atributos[5]);

                    Contenido contenido; //Instanciamos un objeto Contenido

                    //Si el tipo de Contenido es PELICULA, instanciamos e inicializamos un objeto Pelicula
                    //Si el tipo de Contenido es DOCUMENTAL, primero obtenemos el atributo narrador,
                    //para luego instanciar e inicializar el objeto Documental
                    //* Aqui tenemos el POLIMORFISMO en su maxima expresion
                    if ( "PELICULA".equals(tipoContenido) ) {
                        contenido = new Pelicula(titulo, duracion, genero, calificacion);
                    } else {
                        String narrador = atributos[6];
                        contenido = new Documental(titulo, duracion, genero, calificacion, narrador);
                    }
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




/*
¿Qué es el polimorfismo en programación orientada a objetos?

El polimorfismo es el cuarto pilar fundamental de POO. Permite tratar distintos tipos de objetos —como películas y documentales— como si fueran del
mismo tipo, siempre y cuando compartan una estructura base o hereden de una clase común (Contenido).

- Puedes poner películas y documentales juntos en una lista de tipo Contenido.
- El sistema luego decide qué lógica aplicar a cada uno según su tipo en tiempo de ejecución.
- Favorece la reutilización de código y la ampliación del sistema con nuevas clases.

¿Cómo ayuda el operador instanceof a determinar el tipo real de un objeto?

El operador instanceof permite, al momento de ejecutar el programa, saber si un objeto es de un tipo específico. Esto es útil cuando se tiene una
lista de objetos de tipo base, pero necesitas aplicar lógica exclusiva a, por ejemplo, un documental.

- Permite diferenciar películas de documentales dentro de la misma colección según su tipo real.
- Usando instanceof, es posible adaptar el proceso de guardado y lectura de archivos según el tipo de contenido.
- El patrón pattern variable permite simplificar el uso de instanceof y el casting inmediato.

*/