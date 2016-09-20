package Automata;

import java.util.Scanner;

/**
 * Created by Samuel on 18/09/2016.
 * Esta clase funciona como un segundo main.
 */



public class Principal {

    boolean debugAFN = false;

    /*Variables globales*/

    boolean printAFN = false; /*Si esta variable es verdadera, se dibuja el AFN*/
    boolean simularAFN = false; /*Si esta variable es verdadera, se hace la simulacion del AFN*/

    /*Se cera una instancia de un analizador de expresiones regulares*/
    private RegexAnalyzer regexAnalyzer;


    public static void main(String[] args){
        Principal principal = new Principal();
    }

    public Principal(){
        /*Expresion regular que se va a analizar*/
        String cadena = "";
        if (debugAFN == true){
            System.out.println("Se esta en modo DEBUG, la expresion regular a analizar es: ");
            cadena = "(b|b)*abb(a|b)*";
            System.out.println(cadena);

            OpExtra.leerPantalla();
        }
        else{
            System.out.println("(b|b)*abb(a|b)*");
            System.out.println("Ingrese la expresion regular: ");
            Scanner scaner = new Scanner(System.in);
            cadena = scaner.nextLine();
        }

        regexAnalyzer = new RegexAnalyzer(cadena);
        regexAnalyzer.crearAFN();
        regexAnalyzer.imprimirEstadoDelAFN();
        regexAnalyzer.generarTextoAFN();
        regexAnalyzer.graficarGrafoAFN();


        if (regexAnalyzer.simularAFN(cadena) == true){
            System.out.println("La cadena es aceptada por el automata");
        }
        else{
            System.out.println("La cadena no es aceptada por el automata");
        }
    }
}
