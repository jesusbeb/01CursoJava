package org.example.contenido;

//Documental extiende de Contenido e implementa de Promocionable, por lo que
//tendra que implementar los metodos de la clase que implementa
public class Documental extends Contenido implements Promocionable{
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

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo el documental " +getTitulo() +" narrado por " +getNarrador() +"...");
    }

    @Override
    public String promocionar() {
        return "Descubre el Documental " + this.getTitulo() + " narrado por " + narrador + ". ¡Ahora en PlatziPlay!";
    }

    //Getter

    public String getNarrador(){
        return narrador;
    }

}
