package org.example.contenido;

import java.time.LocalDate;

//Renombramos la clase Pelicula por Contenido
//abstract hace que la clase Contenido no se pueda instanciar o crear dentro del proyecto
//
public abstract class Contenido {
    //Atributos
    private String titulo;
    private String descripcion;
    private int duracion;
    private Genero genero;
    private LocalDate fechaEstreno;
    private double calificacion;
    private boolean disponible;

    //Constructor
    public Contenido(String titulo, int duracion, Genero genero){
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.fechaEstreno = LocalDate.now();
        this.disponible = true;
    }

    public Contenido(String titulo, int duracion, Genero genero, double calificacion){
        this(titulo, duracion, genero); //Se usa el constructor anterior, para no repetir su codigo aqui
        this.calificar(calificacion); //Puedo llamar un metodo del objteto dentro del constructor
    }

    //Metodos

    //Metodo abstracto que queda vacio y hace que las clases hijas implementen su
    //propio metodo reproducir() y lo sobreescriban
    //Para poder crear un metodo abstracto la clase debe ser abstracta tambien
    public abstract void reproducir();

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

    //Getters

    public String getTitulo(){
        return titulo;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public int getDuracion(){
        return duracion;
    }

    public Genero getGenero(){
        return genero;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public double getCalificacion(){
        return calificacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    //Setters

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}



