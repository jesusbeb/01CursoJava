package org.example.plataforma;

import org.example.contenido.Genero;
import org.example.contenido.Contenido;
import org.example.contenido.ResumenContenido;
import org.example.excepcion.PeliculaExistenteException;
import org.example.util.FileUtils;

import java.util.*;

//Esta clase se encarga de administrar los objetos de Contenido de la plataforma Platzi Play
public class Plataforma {
    //Atributos
    //Map, usamos Contenido como clave y como valor el numero de visualizaciones.
    //El valor del Map no puede ser una variable primitiva
    private String nombre;
    private List<Contenido> contenido;
    private Map<Contenido, Integer> visualizaciones;

    //Constructor
    public Plataforma(String nombre){
        this.nombre = nombre;
        this.contenido = new ArrayList<>(); //Inicializamos la lista, si no provoca NullPointerException. ArrayList es clase hija de List
        this.visualizaciones = new HashMap<>(); //Inicializamos el Map. HashMap es una clase hija de Map
    }

    //Metodos

    public void agregar(Contenido elemento){
        //Buscamos si el elemento a agregar ya existe
        Contenido contenido = this.buscarPorTitulo(elemento.getTitulo());

        //Si ya existe lanzamos la excepcion con la palabra throw
        //Instanciamos la excepcion y le enviamos como argumento el titulo
        if (contenido != null){
            throw new PeliculaExistenteException(elemento.getTitulo());
        }

        FileUtils.escribirContenido(elemento); //Se agrega el contenido a un archivo plano de texto
        this.contenido.add(elemento); //se agrega el elemento a la lista de elementos
    }

    //Llenamos el Map con el metodo getOrDefault, enviando el elemento que se va a
    //reproducir y agregando un valor por default en caso de no encontrar el valor de Elemento
    public void reproducirContenido(Contenido contenido){
        int contador = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " ha sido reproducido " + contador + " veces.");

        //Agregamos esta reproduccion del contenido a su contador
        this.contarVisualizaciones(contenido); //this solo para aclarar que es un metodo de esta clase, no es necesario
        contenido.reproducir(); //metodo de Contenido
    }

    //Metodo privado porque solo se usara dentro de esta clase
    private void contarVisualizaciones(Contenido contenido){
        int contador = visualizaciones.getOrDefault(contenido, 0);
        visualizaciones.put(contenido, contador + 1); //con put, agregamos al Map el Contenido y +1 a las visualizaciones que ya tenga
    }

    public List<String> getTitulos(){
        return contenido.stream() //Recorremos de manera funcional "contenido" con stream
                .map(Contenido::getTitulo) //map crea un nuevo stream de String con los titulos de Contenido
                .toList(); //El stream de String que obtuvo map se convierte a un List
    }

    //Metodo que retonar una lista de tipo record ResumenContenido, parecido a getTitulos
    public List<ResumenContenido> getResumenes(){
        return contenido.stream()
                .map(c -> new ResumenContenido(c.getTitulo(), c.getDuracion(), c.getGenero()))
                .toList();
    }

    public Contenido buscarPorTitulo(String titulo){
        //stream recorre una lista y permite aplicar acciones especificas sobre los elementos
        return contenido.stream() //recorremos la lista contenido y cada elemento lo identificamos como elemento
                .filter(elemento -> elemento.getTitulo().equalsIgnoreCase(titulo)) // filtramos por el titulo a buscar
                .findFirst() // retornamos el primer resultado encontrado
                .orElse(null); //si no se encontro coincidencia, retornamos nulo
    }

    public List<Contenido> buscarPorGenero(Genero genero){
        return contenido.stream() //stream recorre la lista
                .filter(movie -> movie.getGenero().equals(genero)) //filtramos por genero
                .toList(); //retornamos los resultados en un List
    }

    //Obtenemos una lista de Contenido mejor valorado
    public List<Contenido> getPopulares(int cantidad){
        return contenido.stream()
                .sorted(Comparator.comparingDouble(Contenido::getCalificacion).reversed()) //Ordenamos por calificacion que esta en Double de menor a mayor y luego se revierte de mayor a menor
                .limit(cantidad) //Obtenemos solo los primeros del stream, segun se indique la cantidad
                .toList(); //convertimos a lista
    }

    //Duracion total de todo el contenido de la plataforma
    public int getDuracionTotal(){
        return contenido.stream() //transformarmos la lista contenido en un stream
                .mapToInt(Contenido::getDuracion) // map crea un nuevo stream de int
                .sum(); //sumamos todos los int del stream
    }

    public void eliminarContenido(Contenido contenido){
        this.contenido.remove(contenido);
    }

    //Getters

    public String getNombre(){
        return nombre;
    }

    public List<Contenido> getContenido(){
        return contenido;
    }
}
