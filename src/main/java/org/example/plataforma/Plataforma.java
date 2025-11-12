package org.example.plataforma;

import org.example.contenido.Pelicula;

import java.util.ArrayList;
import java.util.List;

//Esta clase se encarga de administrar los objetos Pelicula en una List
public class Plataforma {
    //Atributos
    private String nombre;
    private List<Pelicula> contenido;

    //Constructor
    public Plataforma(String nombre){
        this.nombre = nombre;
        this.contenido = new ArrayList<>(); //Inicializamos la lista, Si no provoca NullPointerException
    }

    //Metodos

    public void agregar(Pelicula elemento){
        this.contenido.add(elemento); //se agrega la pelicula a la lista de peliculas
    }

    public void mostrarTitulos(){
        //forEach es parte de la interface List
        //forEach, para cada elemento de la List contenido, se le identificara como movie y
        //se imprimira su atributo Titulo
        // -> es una expresion lambda. Una expresion lambda es una forma corta de escribir un metodo
        contenido.forEach(movie -> System.out.println(movie.getTitulo()));
    }

    public Pelicula buscarPorTitulo(String titulo){
        //stream recorre una lista y permite aplicar acciones especificas sobre los elementos
        return contenido.stream() //recorremos la lista contenido y cada elemento lo identificamos como pelicula
                .filter(pelicula -> pelicula.getTitulo().equalsIgnoreCase(titulo)) // filtramos por el titulo a buscar
                .findFirst() // retornamos el primer resultado encontrado
                .orElse(null); //si no se encontro coincidencia, retornamos nulo

    }

    public List<Pelicula> buscarPorGenero(String genero){
        return contenido.stream() //stream recorre la lista
                .filter(movie -> movie.getGenero().equalsIgnoreCase(genero)) //filtramos por genero
                .toList(); //retornamos los resultados en un List
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
