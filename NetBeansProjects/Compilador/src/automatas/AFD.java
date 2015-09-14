/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Moises Urias
 */
public class AFD {
    
    private ArrayList<Subset> estados = new ArrayList<Subset>(); 
    
    private ArrayList<TransicionAfd> transiciones = new ArrayList<TransicionAfd>();
    
    
    
    
    
    
    /**
     * Constructor de un AFD con base en un automata
     * @param AFN 
     */
    public AFD(Automata afn)
    {
        System.out.println("\n\nDentro de constructor del afd");
        
        /*Verifica que aun existan estados no marcados en la pila.
        Se empieza con true, por el primer estado de la pila*/
        boolean hayEstadosNoMarcados = true;
        
        
        /*Se busca el estado inicial del afn*/
        int nodoinicial =0;
        
        for (int i=0;i<afn.getEstados().size();i++)
        {
            /*Se obtiene el nodo inicial de AFN*/
            if (afn.getEstados().get(i).iseInicial() == true){
                System.out.println("Estado inicial CONSTRUCTOR AFD: " + afn.getEstados().get(i).getId());
                nodoinicial = afn.getEstados().get(i).getId();
            }
        }
        
        
        /*Una ves ya se ha encontrado el estado inicial del afn, se crea un 
          subset con ese estado*/
        Subset subset1 = new Subset(nodoinicial,afn.getTransiciones());
        
        /*Se hace un eClosure de ese estado*/
        subset1 = OpExtra.eClosure(subset1);
        
        
        /*Se agrega el subconjunto inicial a una lista de estados iniciales*/
        estados.add(subset1);
        
        
        /*Se obtiene el alfabeto del automata*/
        ArrayList<String> alfabeto = afn.getAlfabeto();
        
        
        /*Mientras haya estados no marcados, se hace eclosure y move*/
        while(hayEstadosNoMarcados == true)
        {
            System.out.println("Dentro del ciclo while del afd");
            
            /*Se recorren todos los estados*/
            
            for (int h=0; h<estados.size();h++)
            {
                /*Se toma el estado actual*/
                Subset estadoActual = estados.get(h);
                
                
                
                       
                /*Como se esta revisando este esatdo, el esatado se marca como
                  marcado*/
                estados.get(h).setMarcado(true);
                
                
                /*Se crea el AFD*/
                for (int i=0; i<alfabeto.size(); i++)
                {
                    /*Se hace un move al estado actual*/
                    estadoActual = OpExtra.move(estadoActual, alfabeto.get(i));
                    
                    /*Se hace eClosure al estado actual*/
                    estadoActual = OpExtra.eClosure(estadoActual);
                    
                    /*Se ordena de menor a mayor el estado actual*/
                    estadoActual.ordenar();
                    
                    /*Se revista si el estado obtenido despues de realizar el 
                      move y el eclosure es un nuevo estado de la lista de
                      estado del AFD o ya existe.
                    
                    Primero se recorren todos los estados que ya posee el AFD*/
                   for(int g = 0; g<this.estados.size();g++)
                    {
                        /*Subset que posteriormente se añadira a los estados del
                           AFD*/
                        Subset sub = new Subset();
                        
                        /*Se toma el arraylist de ese estado*/
                        ArrayList<Integer> estado1 = this.estados.get(g).getNodos();
                        
                        /*Se ordena de menor a mayor*/
                        Collections.sort(estado1);
                        
                        /*Compara si son iguales*/
                        if (estadoActual.equals(estado1))
                        {
                            if (!estados.contains(estado1))
                            {
                                sub.setNodos(estado1);
                                
                                /*Se crea un nueva transicion*/
                                transiciones.add(new TransicionAfd(estadoActual,alfabeto.get(i),sub));
                            }
                        }
                        
                        
                    }
                            
                }
            }
            
            /*Se revisa que ya no hayan estados marcados*/
             boolean b = false;
            for (int g = 0; g<estados.size();g++)
            {
               
                if(estados.get(g).isMarcado() == false)
                {
                    b = true;
                }
            }
            
            hayEstadosNoMarcados = b; 
            
        }
        
        System.out.println(this.estados);
    }
    
    
    
    
    public AFD(Subset subsetI, ArrayList<String> alfabeto)
    {
        
        /**
         * Se hace un eClosure con el estado inicial del afn
         */
        Subset moi = OpExtra.eClosure(subsetI);
        
        //subset moi = OpExtra.move(subsetI, null);
        System.out.println(moi);
        
    }
}
