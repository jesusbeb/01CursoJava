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
        //Para cada elemento del tipo "Pelicula" lo identificamos como "pelicula" y
        //que se encuentra dentro de "contenido", en cada vuelta del ciclo.
        for (Pelicula pelicula : contenido) {
            System.out.println(pelicula.getTitulo());
        }
    }

    public Pelicula buscarPorTitulo(String titulo){
        for (Pelicula pelicula : contenido){
            if (pelicula.getTitulo().equalsIgnoreCase(titulo)) {
                return pelicula;
            }
        }
        return null;
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
