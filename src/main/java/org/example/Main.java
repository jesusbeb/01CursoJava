package org.example;

import org.example.contenido.Genero;
import org.example.contenido.Pelicula;
import org.example.contenido.ResumenContenido;
import org.example.excepcion.PeliculaExistenteException;
import org.example.plataforma.Plataforma;
import org.example.util.ScannerUtils;

import java.util.List;


public class Main {
    //Constantes (se nombra en mayuscula por convencion)
    public static final String NOMBRE_PLATAFORMA = "PLATZY PLAY ";
    public static final String VERSION = "1.0.0.";

    public static final int AGREGAR = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 5;
    public static final int ELIMINAR = 8;
    public static final int SALIR = 9;


    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + VERSION);

        cargarPeliculas(plataforma);

        System.out.println("Mas de " + plataforma.getDuracionTotal() + " minutos de contenido! \n");

        //Menu
        while(true) {
            int opcionElegida = ScannerUtils.capturarNumero("""
                    1. Agregar contenido
                    2. Mostrar todo
                    3. Buscar por titulo
                    4. Buscar por genero
                    5. Ver populares
                    8. Eliminar
                    9. Salir
                    """);
            System.out.println("Opcion elegida: " + opcionElegida);

            switch (opcionElegida) {
                case AGREGAR -> {
                    //Se solicitan los atributos para inicializar la instancia del objeto pelicula
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificacion del contenido");

                    //Tratamos de agregar un nuevo elemento
                    //Si no es posible agregarlo, capturamos la excepcion, obtenemos el mensaje y lo imprimimos
                    try {
                        plataforma.agregar(new Pelicula(nombre, duracion, genero, calificacion));
                    } catch (PeliculaExistenteException e){
                        System.out.println(e.getMessage());
                    }

                }
                case MOSTRAR_TODO -> {
                    List <ResumenContenido> contenidosResumidos = plataforma.getResumenes();
                    contenidosResumidos.forEach(resumen -> System.out.println(resumen));
                }

                case BUSCAR_POR_TITULO -> {
                    String nombreBuscado = ScannerUtils.capturarTexto("Nombre del contenido a buscar");
                    Pelicula peliculaEncontrada = plataforma.buscarPorTitulo(nombreBuscado);

                    if ( peliculaEncontrada != null){
                        System.out.println(peliculaEncontrada.obtenerFichaTecnica());
                    } else {
                        System.out.println(nombreBuscado.toUpperCase() + " No existe dentro de " +plataforma.getNombre());
                    }
                }

                case BUSCAR_POR_GENERO -> {
                    //Convertimos el String capturado en un enum Genero
                    Genero generoBuscado = ScannerUtils.capturarGenero("Nombre del genero a buscar");
                    List<Pelicula> peliculasPorGenero = plataforma.buscarPorGenero(generoBuscado);

                    System.out.println(peliculasPorGenero.size() + " encontradas para el genero " + generoBuscado );
                    peliculasPorGenero.forEach(pelicula -> System.out.println(pelicula.obtenerFichaTecnica() + "\n"));
                }

                case VER_POPULARES -> {
                    int cantidad = ScannerUtils.capturarNumero("Cantidad de resultados a mostrar");
                    List<Pelicula> peliculasPopulares = plataforma.getPopulares(cantidad);
                    peliculasPopulares.forEach(movie -> System.out.println( +cantidad + " MAS POPULARES: \n" + movie.obtenerFichaTecnica() + "\n"));
                }

                case ELIMINAR -> {
                    String nombreAEliminar = ScannerUtils.capturarTexto("Nombre del contenido a eliminar");
                    Pelicula peliculaAEliminar = plataforma.buscarPorTitulo(nombreAEliminar);

                    if ( peliculaAEliminar!= null){
                        plataforma.eliminarPelicula(peliculaAEliminar);
                        System.out.println(nombreAEliminar.toUpperCase() + " ELIMINADO!");
                    } else {
                        System.out.println(nombreAEliminar.toUpperCase() + " No existe dentro de " +plataforma.getNombre());
                    }
                }

                case SALIR -> System.exit(0);
            }
        }



//        //Pelicula pelicula --> Declaracion, new Pelicula --> Instanciacion, (    ) --> Inicializacion
//        //Pelicula pelicula = new Pelicula("El señor de los anillos", 120, "Fantasia", 4.7);
    }


    private static void cargarPeliculas(Plataforma plataforma) {
        plataforma.agregar(new Pelicula("Shrek", 90, Genero.ANIMADA));
        plataforma.agregar(new Pelicula("Inception", 148, Genero.CIENCIA_FICCION));
        plataforma.agregar(new Pelicula("Titanic", 195, Genero.DRAMA, 4.6));
        plataforma.agregar(new Pelicula("John Wick", 101, Genero.ACCION));
        plataforma.agregar(new Pelicula("El Conjuro", 112, Genero.TERROR, 3.0));
        plataforma.agregar(new Pelicula("Coco", 105, Genero.ANIMADA, 4.7));
        plataforma.agregar(new Pelicula("Interstellar", 169, Genero.CIENCIA_FICCION, 5));
        plataforma.agregar(new Pelicula("Joker", 122, Genero.DRAMA));
        plataforma.agregar(new Pelicula("Toy Story", 81, Genero.ANIMADA, 4.5));
        plataforma.agregar(new Pelicula("Avengers: Endgame", 181, Genero.ACCION, 3.9));
    }

}