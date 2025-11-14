package org.example.util;

import org.example.contenido.Genero;

import java.util.Scanner;

public class ScannerUtils {
    //Constante global de la clase de tipo Scanner
    public static final Scanner SCANNER = new Scanner(System.in);

    //Estos 3 metodos seran muy utiles para cuando estemos capturando
    //informacion por teclado y no haya que estar replicando codigo

    public static String capturarTexto(String mensaje) {
        System.out.println(mensaje + ": ");
        return SCANNER.nextLine(); //captura texto
    }

    public static int capturarNumero(String mensaje) {
        System.out.println(mensaje + ": ");

        //En caso de no ingresar un entero
        while (!SCANNER.hasNextInt()){
            System.out.println("Opción no válida. Ingrese una de las siguientes opciones:\n" + mensaje + ": ");
            SCANNER.next(); //El valor no valido que se ingreso, se descarta
        }

        int dato = SCANNER.nextInt(); //Captura entero por teclado
        SCANNER.nextLine(); //Para ignorar el enter que da el usuario despues de introducir el entero
        return dato; //retorna lo capturado por teclado
    }

    public static double capturarDecimal(String mensaje) {
        System.out.println(mensaje + ": ");

        while (!SCANNER.hasNextDouble()){
            System.out.println("Opción no válida. Ingrese una de las siguientes opciones:\n" + mensaje + ": ");
            SCANNER.next();
        }

        double dato = SCANNER.nextDouble();
        SCANNER.nextLine();
        return dato;
    }

    public static Genero capturarGenero(String mensaje){
        //While estara repitiendose hasta que ingresen un genero correcto
        while (true){
            //Mostramos los generos existentes
            //Recorremos el enum Genero para obtener los valores e imprimirlos
            System.out.println(mensaje + "... Opciones");
            for (Genero genero : Genero.values()){
                System.out.println("- " + genero.name());
            }
            System.out.println("Cual quieres?");

            //Capturamos el genero solicitado por teclado
            String entrada = SCANNER.nextLine();

            //Tratamos de convertir el texto capturado en un valor de Genero
            //Si no es posible, lanzamos una excepcion
            try {
                return Genero.valueOf(entrada.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Genero no aceptado.");
            }
        }
    }


}