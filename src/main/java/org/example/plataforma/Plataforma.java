package org.example.plataforma;

import org.example.contenido.Genero;
import org.example.contenido.Pelicula;
import org.example.contenido.ResumenContenido;
import org.example.excepcion.PeliculaExistenteException;
import org.example.util.FileUtils;

import java.util.*;

//Esta clase se encarga de administrar los objetos Pelicula en una List
public class Plataforma {
    //Atributos
    //Map, usamos Pelicula como clave y como valor el numero de visualizaciones.
    //El valor del Map no puede ser una variable primitiva
    private String nombre;
    private List<Pelicula> contenido;
    private Map<Pelicula, Integer> visualizaciones;

    //Constructor
    public Plataforma(String nombre){
        this.nombre = nombre;
        this.contenido = new ArrayList<>(); //Inicializamos la lista, Si no provoca NullPointerException. ArrayList es clase hija de List
        this.visualizaciones = new HashMap<>(); //Inicializamos el Map. HashMap es una clase hija de Map
    }

    //Metodos

    public void agregar(Pelicula elemento){
        //Buscamos si el elemento a agregar ya existe
        Pelicula contenido = this.buscarPorTitulo(elemento.getTitulo());

        //Si ya existe lanzamos la excepcion con la palabra throw
        //Instanciamos la excepcion y le enviamos como argumento el titulo
         if (contenido != null){
            throw new PeliculaExistenteException(elemento.getTitulo());
        }

        FileUtils.escribirPelicula(elemento); //Se agrega la pelicula a un archivo plano de texto
        this.contenido.add(elemento); //se agrega la pelicula a la lista de peliculas
    }

    //Llenamos el Map con el metodo getOrDefault, enviando la pelicula que se va a
    //reproducir y agregando un valor por default en caso de no encontrar el valor de Pelicula
    public void reproducirPelicula(Pelicula pelicula){
        int contador = visualizaciones.getOrDefault(pelicula, 0);
        System.out.println(pelicula.getTitulo() + " ha sido reproducido " + contador + " veces.");

        //Agregamos esta reproduccion de pelicula a su contador
        this.contarVisualizaciones(pelicula); //this solo para aclarar que es un metodo de esta clase, no es necesario
        pelicula.reproducir(); //metodo de Pelicula
    }

    //Metodo privado porque solo se usara dentro de esta clase
    private void contarVisualizaciones(Pelicula pelicula){
        int contador = visualizaciones.getOrDefault(pelicula, 0);
        visualizaciones.put(pelicula, contador + 1); //con put, agregamos al Map la Pelicula y +1 a las visualizaciones que ya tenga
    }

    public List<String> getTitulos(){
        return contenido.stream() //Recorremos de manera funcional "contenido" con stream
                .map(Pelicula::getTitulo) //map crea un nuevo stream de String con los titulos de las peliculas
                .toList(); //El stream de String que obtuvo map se convierte a un List
    }

    //Metodo que retonar una lista de tipo record ResumenContenido, parecido a getTitulos
    public List<ResumenContenido> getResumenes(){
        return contenido.stream()
                .map(c -> new ResumenContenido(c.getTitulo(), c.getDuracion(), c.getGenero()))
                .toList();
    }

    public Pelicula buscarPorTitulo(String titulo){
        //stream recorre una lista y permite aplicar acciones especificas sobre los elementos
        return contenido.stream() //recorremos la lista contenido y cada elemento lo identificamos como pelicula
                .filter(pelicula -> pelicula.getTitulo().equalsIgnoreCase(titulo)) // filtramos por el titulo a buscar
                .findFirst() // retornamos el primer resultado encontrado
                .orElse(null); //si no se encontro coincidencia, retornamos nulo

    }

    public List<Pelicula> buscarPorGenero(Genero genero){
        return contenido.stream() //stream recorre la lista
                .filter(movie -> movie.getGenero().equals(genero)) //filtramos por genero
                .toList(); //retornamos los resultados en un List
    }

    //Obtenemos una lista de peliculas mejor valoradas
    public List<Pelicula> getPopulares(int cantidad){
        return contenido.stream()
                .sorted(Comparator.comparingDouble(Pelicula::getCalificacion).reversed()) //Ordenamos por calificacion que esta en Double de menor a mayor y luego se revierte de mayor a menor
                .limit(cantidad) //Obtenemos solo los primeros del stream, segun se indique la cantidad
                .toList(); //convertimos a lista
    }

    //Duracion total de todo el contenido de la plataforma
    public int getDuracionTotal(){
        return contenido.stream() //transformarmos la lista contenido en un stream
                .mapToInt(Pelicula::getDuracion) // map crea un nuevo stream de int
                .sum(); //sumamos todos los int del stream
    }

    public void eliminarPelicula(Pelicula pelicula){
        this.contenido.remove(pelicula);
    }

    //Getters

    public String getNombre(){
        return nombre;
    }

    public List<Pelicula> getContenido(){
        return contenido;
    }
}
