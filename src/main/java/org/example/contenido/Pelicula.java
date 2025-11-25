package org.example.contenido;

//La clase Pelicula hereda de Contenido junto con todos sus atributos y metodos
public class Pelicula extends Contenido{

    //Constructor, tiene que ser igual al constructor de la clase Padre
    public Pelicula(String titulo, int duracion, Genero genero, double calificacion) {
        super(titulo, duracion, genero, calificacion); //Los parametros se envian al constructor de la clase Padre
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo la pelicula " +getTitulo() + "...");
    }
}
