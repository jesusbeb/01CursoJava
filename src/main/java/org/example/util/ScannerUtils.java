package org.example.util;

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
}