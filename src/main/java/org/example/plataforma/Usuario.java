package org.example.plataforma;

import org.example.contenido.Pelicula;

import java.time.LocalDateTime;

public class Usuario {
    public String nombre;
    public String email;
    public LocalDateTime fechaRegistro;

    //Constructor
    public Usuario(String nombre, String email){
        this.nombre = nombre;
        this.email = email;
    }

    public void ver(Pelicula pelicula){
        System.out.println(nombre + " esta viendo");
        pelicula.reproducir();
    }
}
