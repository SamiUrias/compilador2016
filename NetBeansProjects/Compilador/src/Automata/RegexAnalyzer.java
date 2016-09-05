/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automata;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Samuel Urias
 */
public class RegexAnalyzer {
     private static String p; /*Direccion donde se crea la imagen del automata*/
     
      /*Almacena un arraylist con el alfabeto del automata*/
        private ArrayList<String> alfabeto;
        private int nodoinicialafn = 0; /*Almacena el nodo inicial del AFN*/
        private int nodofinalafn = 0; /*Almacena el nodo final del AFN*/
        
        /*Permite realizar una simulacion del AFN*/ 
        private Simulador simulador;
        
         /*Esta variable almacena el tiempo al principio de la ejecución del 
           programa*/
        long time1 = System.currentTimeMillis();
        
        public void analizar(){
            System.out.println("(b | b)*abb(     a  | b ) *");
            System.out.println("Ingrese la expresion regular: ");
        
        
            Scanner scaner = new Scanner(System.in);
            String cadena = scaner.nextLine();

            //String cadena = "(b | b)*abb(     a  | b ) * ";

            cadena = cadena.trim();
            cadena = cadena.replace(" ", "");

            cadena = (RegexConverter.infixToPostfix(cadena));

            System.out.println("Cadena con posfix: \n" + cadena+"\n\n\n");
        }
}
