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
        /*Recorre todas las nodos del subset*/
        for (int i=0;i<subset.getNodos().size();i++)
        {
            /*Toma el nodo actual del ciclo*/
            int nodo = subset.getNodos().get(i);
            
            /*Se recorren todas las transiciones del subset para saber si alguna
               de esas transiciones corresponde al nodo seleccionado*/
            for (int j=0;j<subset.getTransiciones().size();j++)
            {
               
                /*Si la el nodo corresponde al nodo inicial de la transicion,
                   entonces se añade el nodo final de la transicion al subset
                   y se realiza un */
                if (nodo==subset.getTransiciones().get(j).getNodoInicial())
                {
                   if (subset.getTransiciones().get(j).getSimbolo().equals("!"))
                   {
                       /*Se añade el nodo ennconrado al subset*/
                       //subset.add(subset.getTransiciones().get(j).getNodoFinal());
                       
                       if(!(subset.Nodos.contains(subset.getTransiciones().get(j).getNodoFinal())))
                        {
                            subset.getTransiciones().get(j).getNodoFinal();
                        }
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
        
        return subset;
    }
    
    
    
    
    public static Subset move(Subset subset, String simbolo)
    {
        Subset subconjunto = new Subset();
        
        /*Se revisan todos los nodos del subset*/
        for (int i =0; i<subset.Nodos.size();i++)
        {
            int nodo = subset.getNodos().get(i);
            
            /*Se revisan todas las transiciones del subset*/
            for (int j =0;j<subset.getTransiciones().size();j++)
            {
                /*Se se encuentra una transicion en la que este implicado
                  el nodo, entonces se procede a verificar si esa transicion
                  ourre con el simbolo que se esta ingresando*/
                if (nodo==subset.getTransiciones().get(j).getNodoInicial())
                    {
                       if (subset.getTransiciones().get(j).getSimbolo().equals(simbolo))
                       {
                           if(!(subconjunto.getNodos().contains(subset.getTransiciones().get(j).getNodoFinal())))
                           {
                               subconjunto.add(subset.getTransiciones().get(j).getNodoFinal());
                           }
                       }
                    }
            }

        }
        
        return subconjunto;
    }
    
    
}
