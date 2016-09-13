/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automata;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Esta clase tiene operaciones adicionales que son de utilidad al programa
 * principal
 * @author Moises Urias
 */
public class OpExtra {
    
    /**
     * Realiza un eClosure en base a un subset
     * @param subset 
     * @return  subset
     */
    public static Subset eClosure(Subset subset)
    {  
       // System.out.println(subset.toString());   //DEBUG


        /*Recorre todas las nodos del subset*/
        for (int i=0;i<subset.getNodos().size();i++)
        {
            /*Toma el nodo actual del ciclo*/
            int nodo = subset.getNodos().get(i);
            
            /*Se recorren todas las transiciones del subset para saber si alguna
               de esas transiciones corresponde al nodo seleccionado*/
            for (int j=0;j<subset.getTransiciones().size();j++)
            {
               
                /*Si el nodo corresponde al nodo inicial de la transicion,
                   entonces se añade el nodo final de la transicion al subset
                   y se realiza un */
                if (nodo==subset.getTransiciones().get(j).getNodoInicial())
                {
                   if (subset.getTransiciones().get(j).getSimbolo().equals("!"))
                   {
                       /*Se añade el nodo ennconrado al subset actual*/
                       if(!(subset.Nodos.contains(subset.getTransiciones().get(j).getNodoFinal())))
                        {
                            subset.add(subset.getTransiciones().get(j).getNodoFinal());
                            
                            
                        /*Se crea un nuevo subset en base con base en el nodo 
                         encontrado*/
                        Subset temp = new Subset(subset.getTransiciones().
                                get(j).getNodoFinal(),subset.getTransiciones());
                        
                        /*Se hace un e-Closure del nodo encontrado, y se combina 
                         con el subset actual*/
                        subset.combinarEClosure(eClosure(temp));
                        }
                       
                   }
                }
            }
        }
//        System.out.println("eClosure: " + subset.toString());
        return subset;
    }
    
    
    
    /**
     * Ete metodo se utiliza para realizar la operacion mover en la construccion
     *  de subconjuntos.
     * @param subset
     * @param simbolo
     * @return subset
     */

    public static Subset mover(Subset subset, String simbolo)
    {
        /*Se obtienen las transiciones del subset*/
        Subset subconjunto = new Subset(subset.getTransiciones());
        
        /*Se revisan todos los nodos del subset*/
        for (int i =0; i<subset.Nodos.size();i++)
        {
            int nodo = subset.getNodos().get(i);
            //Se imprimen todas las transiciones del automata
            //System.out.println("Dentro del move\nTodas las transiciones del subset");
            System.out.println(subset.getNombre_subset());
            System.out.println(subset.getTransiciones());
            /*Se revisan todas las transiciones del subset*/
            for (int j =0;j<subset.getTransiciones().size();j++)
            {
                /*Se se encuentra una transicion en la que este implicado
                  el nodo, entonces se procede a verificar si esa transicion
                  ourre con el simbolo que se esta ingresando*/
                if (nodo==subset.getTransiciones().get(j).getNodoInicial())
                    {
                        /*Revisa si la transicion encontrada se realiza mediante
                           el simbolo ingresado*/
                       if (subset.getTransiciones().get(j).getSimbolo().equals(simbolo))
                       {
                           /*Si el estado final no se encuentra entre los estados
                             del subset, entonces se agrega el estado encontrado*/
                           if(!(subconjunto.getNodos().contains(subset.getTransiciones().get(j).getNodoFinal())))
                           {
                               /*Se añade el estado al subconjunto*/
                               subconjunto.add(subset.getTransiciones().get(j).getNodoFinal());

                               System.out.println("TRansicio encontrada: ");
                               System.out.println(subset.getTransiciones().get(j));
                           }
                       }
                    }
            }

        }
        
       // System.out.println("Move: " + subconjunto);
        return subconjunto;
    }



    /**
     * Este metodo se utiliza en la simulacion del AFD, ya que el move para la simulacion del AFN se basa en los
     * */
    public static void moverAFD(){

    }
    
    /**
     * Esta funcion devuelve el alfabeto que tiene el automata
     * @param cadena
     * @return 
     */
    public static ArrayList alfabeto (String cadena)
    {
        
        ArrayList<String> alfabeto = new ArrayList<String>();
        
        int j = 0; //Contador del ciclo
        while (j<cadena.length())
        {
            /*Es la subcadena */
            String simbolo = cadena.substring(j, j+1);
            
           if( esAlfabeto(simbolo) == true)
           {
               if(!alfabeto.contains(simbolo))
               {
                   alfabeto.add(simbolo);
               }
           }
           
           /*Aumenta en uno el contador del ciclo*/
           j++;
        }
        
        return alfabeto;
    }
    
    private static boolean esAlfabeto(String simbolo)
    {
        String[] operadores = {"(","|","?",".","*","+","^"};
        boolean operador = true; /*Si se ha reconocido como operador o no*/


        int i = 0; /*Contador de operadores*/

        while (i<operadores.length)
        {
           if (simbolo.equals(operadores[i]))
           {
               operador = false;
           }

           i++; /*Aumenta uno el contador 'operador'*/

        }

        /*Devuelve el resultado de evaluar el simbolo para saber si es un
            operador o no*/
        return operador;
    }


    /**
     * Este metodo permite debugear variables
     * */
    public static void debug(Object variable){
        System.out.println("****************************** DEBUG ********************************");
        System.out.print(variable.toString());
        System.exit(0);
    }


    /**
     * Este metodo se utiliza para leer en pantalla
     * */
    public static void leerPantalla(){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n--> Presione una tecla para continuar...");
        scan.nextLine();
    }

    public static void imprirLinea(){
        System.out.println("---------------------------------------------------------------------------------");
    }

    public static void imprirLineaCorta(){
        System.out.println("---------------------");
    }


    public static String reemplazarNumerosPorLetras(int numero){
        int nuevo_numero = numero + 64;
        return Character.toString((char)nuevo_numero);

    }
}
