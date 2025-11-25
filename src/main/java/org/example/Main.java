package org.example;

import org.example.contenido.*;
import org.example.excepcion.PeliculaExistenteException;
import org.example.plataforma.Plataforma;
import org.example.util.FileUtils;
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
    public static final int REPRODUCIR = 6;
    public static final int BUSCAR_POR_TIPO = 7;
    public static final int ELIMINAR = 8;
    public static final int SALIR = 9;


    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + VERSION);

        cargarContenido(plataforma);

        System.out.println("Mas de " + plataforma.getDuracionTotal() + " minutos de contenido! \n");
        plataforma.getContenidoPromocionable().forEach(cp -> System.out.println( cp.promocionar() +"\n" ));

        //Menu
        while(true) {
            int opcionElegida = ScannerUtils.capturarNumero("""
                    1. Agregar contenido
                    2. Mostrar todo
                    3. Buscar por titulo
                    4. Buscar por genero
                    5. Ver populares
                    6. Reproducir
                    7. Buscar por tipo de contenido
                    8. Eliminar
                    9. Salir
                    """);
            System.out.println("Opcion elegida: " + opcionElegida);

            switch (opcionElegida) {
                case AGREGAR -> {
                    int tipoDeContenido = ScannerUtils.capturarNumero(
                            "Que tipo de conenido quieres agregar? \n 1. Pelicula \n 2. Documental");
                    //Se solicitan los atributos para inicializar la instancia del objeto pelicula
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificacion del contenido");

                    //Tratamos de agregar un nuevo elemento
                    //Si no es posible agregarlo, capturamos la excepcion, obtenemos el mensaje y lo imprimimos
                    try {
                        if (tipoDeContenido == 1){
                            plataforma.agregar(new Pelicula(nombre, duracion, genero, calificacion));
                        } else {
                            String narrador = ScannerUtils.capturarTexto("Narrador del documental");
                            plataforma.agregar(new Documental(nombre, duracion, genero, calificacion, narrador));
                        }
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
                    Contenido contenidoEncontrada = plataforma.buscarPorTitulo(nombreBuscado);

                    if ( contenidoEncontrada != null){
                        System.out.println(contenidoEncontrada.obtenerFichaTecnica());
                    } else {
                        System.out.println(nombreBuscado.toUpperCase() + " No existe dentro de " +plataforma.getNombre());
                    }
                }

                case BUSCAR_POR_GENERO -> {
                    //Convertimos el String capturado en un enum Genero
                    Genero generoBuscado = ScannerUtils.capturarGenero("Nombre del genero a buscar");
                    List<Contenido> contenidosPorGenero = plataforma.buscarPorGenero(generoBuscado);

                    System.out.println(contenidosPorGenero.size() + " encontradas para el genero " + generoBuscado );
                    contenidosPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                }

                case VER_POPULARES -> {
                    int cantidad = ScannerUtils.capturarNumero("Cantidad de resultados a mostrar");
                    List<Contenido> contenidoPopular = plataforma.getPopulares(cantidad);
                    contenidoPopular.forEach(c -> System.out.println( cantidad + " MAS POPULARES: \n" + c.obtenerFichaTecnica() + "\n"));
                }

                //El metodo reproducir busca un Contenido y lo trata como Contenido, no lo trata como Pelicula o Documental
                case REPRODUCIR -> {
                    String nombreContenido = ScannerUtils.capturarTexto("Nombre del contenido a reproducir");
                    Contenido contenido = plataforma.buscarPorTitulo(nombreContenido);

                    if ( contenido != null){
                        plataforma.reproducirContenido(contenido);
                    } else {
                        System.out.println(nombreContenido.toUpperCase() + " No existe dentro de " +plataforma.getNombre());
                    }
                }

                case BUSCAR_POR_TIPO -> {
                    int tipoDeContenido = ScannerUtils.capturarNumero(
                            "Que tipo de conenido quieres agregar? \n 1. Pelicula \n 2. Documental");

                    if (tipoDeContenido == 1) {
                        List<Pelicula> peliculas = plataforma.getPeliculas();
                        peliculas.forEach(pelicula -> System.out.println(pelicula.obtenerFichaTecnica() + "\n"));
                    } else {
                        List<Documental> documentales = plataforma.getDocumentales();
                        documentales.forEach(documental -> System.out.println(documental.obtenerFichaTecnica() + "\n"));
                    }
                }

                case ELIMINAR -> {
                    String nombreAEliminar = ScannerUtils.capturarTexto("Nombre del contenido a eliminar");
                    Contenido contenidoAEliminar = plataforma.buscarPorTitulo(nombreAEliminar);

                    if ( contenidoAEliminar != null){
                        plataforma.eliminarContenido(contenidoAEliminar);
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


    private static void cargarContenido(Plataforma plataforma) {
        plataforma.getContenido().addAll(FileUtils.leerArchivo()); //Cargamos la List de peliculas
    }

}