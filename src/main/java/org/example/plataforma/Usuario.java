package org.example.plataforma;

import org.example.contenido.Pelicula;

import java.time.LocalDateTime;

public class Usuario {
    private String nombre;
    private String email;
    private LocalDateTime fechaRegistro;

    //Constructor
    public Usuario(String nombre, String email){
        this.nombre = nombre;
        this.email = email;
    }

    //Metodos

    public void ver(Pelicula pelicula){
        System.out.println(nombre + " esta viendo");
        pelicula.reproducir();
    }

    //Getters

    public String getNombre(){
        return nombre;
    }

    public String email(){
        return email;
    }

    public LocalDateTime getFechaRegistro(){
        return fechaRegistro;
    }

    //Setters

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
