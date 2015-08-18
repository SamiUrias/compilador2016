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
        
        
        // TODO code application logic here
        
        /*Esta variable almacena el tiempo al principio de la ejecución del 
           programa*/
        long time = System.currentTimeMillis();
        
        
        System.out.println("REGEX");
        
        
        /*Scanner scaner = new Scanner(System.in);
        String cadena = scaner.nextLine();*/
        
        String cadena = "(b | b)*abb(     a  | b ) * ";
        System.out.println(cadena);
        cadena = cadena.trim();
        cadena = cadena.replace(" ", "");
        
        System.out.println(cadena);
        cadena = (RegexConverter.infixToPostfix(cadena));
        
        System.out.println("Cadena con posfix: \n" + cadena+"\n\n\n");
         
        
        /*Aqui se crea al tuomata*/
        int j=0; /*Contador*/
        Automata aut = new Automata();
        while (j<cadena.length())
        {
            aut.crearAutomata(cadena.substring(j, j+1));
            j++;
        }
        
                
        System.out.println("\nLa cantidad de estados es: " + 
                ContadorNodo.getContador());

        
        aut = aut.automatas.pop(); 
        
        System.out.println("\nCantidad de transiciones: " + aut.transiciones.size());
       
      
       
        
        System.out.println("Las transiciones en el automata son: ");
        for (int i=0; i<aut.transiciones.size();i++)
        {   
            
            System.out.println(i+": "+ aut.transiciones.get(i));
        }
        
        System.out.println("El estado inicial es: " );
        System.out.println("El estado final es: ");
        
        
        
          /*Se resta el tiempo actual del sistema con el tiempo de inicio y ese
           es tiempo total de la ejecucion*/
       time = System.currentTimeMillis()-time;
        System.out.println("\nEl tiempo total del programa es: " + time + " milisegundos\n");
        
        
        ArrayList<Nodo> arnodo = aut.getEstados();
        int nodoinicial =0;
        for (int i=0; i<arnodo.size();i++)
        {
            System.out.println("El nodo: " + arnodo.get(i).getId());
            if (arnodo.get(i).iseInicial() == true){
                System.out.println("Estado inicial: " + arnodo.get(i).getId());
                nodoinicial = arnodo.get(i).getId();
            }
        }
        
        /*Crea un subset*/
        System.out.println("El nodo inicial a introducir en el subset es: " +nodoinicial);
       
        System.out.println("La cantidad de transiciones en el automata es: " + aut.getTransiciones().size());
        Subset subset1 = new Subset(nodoinicial,aut.getTransiciones());
        
        Subset moi = OpExtra.eClosure(subset1);
        System.out.println(moi);
    }
    
   
    
}
