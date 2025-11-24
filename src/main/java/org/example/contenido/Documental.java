package org.example.contenido;

public class Documental extends Contenido{
    //Añadimos un atributo propio de Documental pero no existe para Contenido
    private String narrador;

    //Constructores. Creamos los dos de la clase Padre

    public Documental(String titulo, int duracion, Genero genero) {
        super(titulo, duracion, genero);
    }

    public Documental(String titulo, int duracion, Genero genero, double calificacion, String narrador) {
        super(titulo, duracion, genero, calificacion); //Primero pasamos los valores del constructor de la clase padre
        this.narrador = narrador; //Luego asignamos el valor del atributo de la clase hija. Esto pudo haber sido en cualquiera de los dos constructores
    }

    //Getter

    public String getNarrador(){
        return narrador;
    }

}
