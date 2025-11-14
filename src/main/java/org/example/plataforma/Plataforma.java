package org.example.plataforma;

import org.example.contenido.Genero;
import org.example.contenido.Pelicula;

import java.util.ArrayList;
import java.util.Comparator;
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

    public List<String> getTitulos(){
        return contenido.stream() //Recorremos de manera funcional "contenido" con stream
                .map(Pelicula::getTitulo) //map crea un nuevo stream de String con los titulos de las peliculas
                .toList(); //El stream de String que obtuvo map se convierte a un List
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
