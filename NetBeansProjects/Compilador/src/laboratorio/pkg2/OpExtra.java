/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio.pkg2;

import java.util.ArrayList;

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
        System.out.println("Dentro del eClosure");
        ArrayList<Integer> yaEClosure = new ArrayList<Integer>();
        ArrayList<Integer> noEClosure = new ArrayList<Integer>();
        
        /*Recorre todas las nodos del subset*/
        for (int i=0;i<subset.getNodos().size();i++)
        {
            /*Toma el nodo actual del ciclo*/
            int nodo = subset.getNodos().get(i);
            System.out.println(nodo);
            /*Se recorren todas las transiciones del subset para saber si alguna
               de esas transiciones corresponde al nodo seleccionado*/
            System.out.println("La cantidad de transiciones es: " + subset.getTransiciones().size());
            for (int j=0;j<subset.getTransiciones().size();j++)
            {
                System.out.println("T("+subset.transiciones.get(j)+")");
                /*Si la el nodo corresponde al nodo inicial de la transicion,
                   entonces se añade el nodo final de la transicion al subset
                   y se realiza un */
                if (nodo==subset.getTransiciones().get(j).getNodoInicial())
                {
                   if (subset.getTransiciones().get(j).getSimbolo().equals("!"))
                   {
                       System.out.println("Se ha encontrado una transicion con epsilon");
                       /*Se añade el nodo ennconrado al subset*/
                       subset.add(subset.getTransiciones().get(j).getNodoFinal());
                       
                       /*Se hace un e-Closure del nodo encontrado*/
                       //eClosure(subset.getTransiciones().get(j).getNodoFinal())
                       
                       
                       
                   }
                }
            }
        }
        
        return subset;
    }
    
    
    
}
