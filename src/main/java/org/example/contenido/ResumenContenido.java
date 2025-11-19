package org.example.contenido;

//Record de la clase Pelicula, es como una version mas ligera
public record ResumenContenido(String titulo,
                               int duracion,
                               Genero genero) {
}



/*
¿Qué es un record en Java y para qué sirve?
Un record permite definir una clase inmutable de manera sencilla, perfecta para almacenar datos que
no deben cambiar. Cuando defines un record, Java genera automáticamente:
- Un constructor con los parámetros especificados.
- Métodos de acceso, similares a getters.
- Los métodos equals, toString y hashCode sin necesidad de escribirlos manualmente.

Este recurso es especialmente útil para representar objetos "ligeros" o versiones resumidas de
una entidad más grande, como una película con solo su título, duración y género.
*/