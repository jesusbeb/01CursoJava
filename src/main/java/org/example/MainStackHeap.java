package org.example;

import org.example.contenido.Pelicula;

public class MainStackHeap {
    public static void main(String[] args) {
        Pelicula reyLeon = new Pelicula("El Rey Leon", 135, "Animada");
        Pelicula harryPotter = new Pelicula("Harry Potter", 200, "Fantasia");

        System.out.println("reyLeon: " + reyLeon.titulo);
        System.out.println("harryPotter: " + harryPotter.titulo);

        //Con esta asignacion "reyLeon new Pelicula" ya quedo desaparecido (inalcanzable)
        //reyLeon apunta a donde esta apuntando harryPotter en el heap
        reyLeon = harryPotter;
        System.out.println("----------------------------------");
        System.out.println("reyLeon: " + reyLeon.titulo);
        System.out.println("harryPotter: " + harryPotter.titulo);
        //Por lo tanto cualquier cambio que se haga en cualquiera de las dos variables sera
        //sera visible por todas las variables que esten con esa misma asignacion

        //Por ejemplo ahora solo cambiamos la variable reyLeon y se afecta el objeto harryPotter
        reyLeon.titulo = "El hobbit";
        System.out.println("----------------------------------");
        System.out.println("reyLeon: " + reyLeon.titulo);
        System.out.println("harryPotter: " + harryPotter.titulo);
    }
}


/*
Java tiene dos zonas de memoria: Stack y Heap

En el Stack se guardan:
- Variables de tipo de dato primitivo y referencia a los objetos, pero nunca los objetos mismos.
- Su tamaño se fija al inicio de la ejecucion y nunca cambia
- Si se llena ocurre un StackOverflowError.

En el Heap se almacenan
- Objetos
- Su tamaño se fija al inicio de la ejecucion y es dinamico
- Cuando se llena ocurre el error llamado OutOfMemoryError: Java heap space

Hay una relación directa entre stack y heap: las referencias viven en el stack y apuntan a los objetos
en el heap. Entender esto ayuda a saber por qué ocurren ciertos errores o comportamientos en programas Java.

¿Qué diferencia hay entre pasar datos primitivos y objetos en Java?

Al asignar tipos primitivos, se copia el valor real, generando variables independientes. Por ejemplo:
Si cambias el valor a una variable primitiva copiada, la otra no se afecta.
En cambio, cuando asignas variables de tipo objeto, solo se copia la referencia:

Varias variables pueden apuntar al mismo objeto en el heap.
Si modificas el objeto a través de cualquier variable, los demás también reflejan ese cambio.

Un experimento práctico mostró que si asignas una variable objeto a otra, ambas apuntan al mismo lugar, y
cualquier cambio se ve reflejado siempre. El objeto original se vuelve inaccesible si ninguna variable lo referencia.

¿Qué sucede cuando un objeto ya no tiene referencias en Java?

Cuando ningún elemento en el stack apunta a un objeto en el heap, Java lo considera inalcanzable y está listo para ser
eliminado. Aquí entra el Garbage Collector:
Es un proceso automático de Java que elimina objetos no referenciados del heap.
Se ejecuta en segundo plano y con baja prioridad.
Java permite invocarlo manualmente, pero se recomienda dejar que la JVM decida cuándo y cómo hacerlo, ya que
conoce el mejor momento.

Esta gestión automática evita muchas fugas de memoria y errores por objetos olvidados, facilitando el desarrollo en Java.

¿Por qué es importante conocer el ciclo de vida de los objetos en Java?

Saber cómo se manejan las referencias y el ciclo de vida permite programar de forma más segura y eficiente. Comprender
conceptos como stack, heap y garbage collector ayuda a anticipar problemas como referencias duplicadas, memoria fuera de
control o errores de espacio.


*/
