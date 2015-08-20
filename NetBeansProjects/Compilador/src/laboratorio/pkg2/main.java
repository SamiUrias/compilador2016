/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio.pkg2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Moises Urias
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*Almacena un arraylist con el alfabeto del automata*/
        ArrayList<String> alfabeto;
        
        // TODO code application logic here
        
        /*Esta variable almacena el tiempo al principio de la ejecución del 
           programa*/
        long time = System.currentTimeMillis();
        
        
        System.out.println("Ingrese la expresion regular: ");
        
        
        Scanner scaner = new Scanner(System.in);
        String cadena = scaner.nextLine();
        
        //String cadena = "(b | b)*abb(     a  | b ) * ";
        cadena = cadena.trim();
        cadena = cadena.replace(" ", "");
        
        cadena = (RegexConverter.infixToPostfix(cadena));
        
        System.out.println("Cadena con posfix: \n" + cadena+"\n\n\n");
         
        
        
        
        /*Imprime el alfabeto del automata ingresado */
        System.out.println("El alfabeto del automata es:");
        alfabeto = OpExtra.alfabeto(cadena);
        System.out.println(alfabeto);
        
        /*Se crea el automata*/
        Automata aut = new Automata();
        
        /*Se le asigna al nuevo automata el alfabeto que este utilizara*/
        aut.setAlfabeto(alfabeto);
        
        /*Aqui crean los nodos del automata*/
        int j=0; /*Contador*/
        while (j<cadena.length())
        {
            aut.crearAutomata(cadena.substring(j, j+1));
            j++;
        }
        
                
        System.out.println("\nLa cantidad de estados es: " + 
                ContadorNodo.getContador()+"\n");
 
        aut = aut.automatas.pop(); 

        System.out.println("Las transiciones en el automata son: ");
        for (int i=0; i<aut.transiciones.size();i++)
        {   
            
            System.out.println(i+": "+ aut.transiciones.get(i));
        }
        
         
        System.out.println("\nCantidad de transiciones: " + aut.transiciones.size()+"\n");
        
        ArrayList<Nodo> arnodo = aut.getEstados();
        int nodoinicial =0;
        for (int i=0; i<arnodo.size();i++)
        {
            
            if (arnodo.get(i).iseInicial() == true){
                System.out.println("Estado inicial: " + arnodo.get(i).getId());
                nodoinicial = arnodo.get(i).getId();
            }
            
            if (arnodo.get(i).iseFinal() == true){
                System.out.println("Estado final: " + arnodo.get(i).getId());
            }
        }
        
        
          /*Se resta el tiempo actual del sistema con el tiempo de inicio y ese
           es tiempo total de la ejecucion*/
       time = System.currentTimeMillis()-time;
        System.out.println("\nEl tiempo total del programa es: " + time + " milisegundos\n");
        
        
        
        
        /*Crea un subset*/
        System.out.println("El nodo inicial a introducir en el subset es: " +nodoinicial);
       
        System.out.println("La cantidad de transiciones en el automata es: " + aut.getTransiciones().size());
        
        
        
        AFD afd = new AFD(aut); 
        
       
        
        
    }
    
   
    
}
