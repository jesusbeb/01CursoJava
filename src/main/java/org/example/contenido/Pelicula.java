package org.example.contenido;

import java.time.LocalDate;

public class Pelicula {
    //Atributos
    public String titulo;
    public String descripcion;
    public int duracion;
    public String genero;
    public LocalDate fechaEstreno;
    public double calificacion;
    public boolean disponible;

    //Constructor
    public Pelicula(String titulo, int duracion, String genero){
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.fechaEstreno = LocalDate.now();
        this.disponible = true;
    }

    public Pelicula(String titulo, int duracion, String genero, double calificacion){
        this(titulo, duracion, genero); //Se usa el constructor anterior, para no repetir su codigo aqui
        this.calificar(calificacion); //Puedo llamar un metodo del objteto dentro del constructor
    }

    //Metodo
    public void reproducir(){
        System.out.println("Reproduciendo " +titulo);
    }

    public String obtenerFichaTecnica(){
        return "Titulo: " + titulo + "\n" +
        "Año de estreno: " + fechaEstreno.getYear() + "\n" +
        "Genero: " +genero + "\n" +
        "Calificacion: " + calificacion + "/5";
    }

    //Validamos calificacion entre 0 y 5
    public void calificar(double calificacion){
        if (calificacion >= 0 && calificacion <= 5) {
            this.calificacion = calificacion;
        }
    }

    public boolean esPopular(){
        return calificacion <=4;
    }

}



