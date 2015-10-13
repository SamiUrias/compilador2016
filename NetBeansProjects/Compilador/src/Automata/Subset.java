/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Subset que se utiliza en el eClosure
 * @author Moises Urias
 */
public class Subset{
    /**
     * Un ArrayList<Integer> nodos<p>
     * Contiene un arreglo de los nododos que va a tener el subconjunto
     */
    public ArrayList<Integer> Nodos = new ArrayList<Integer>();
    
    /**
     * Un ArrayList<Transicion> transiciones<p>
     * Contiene un arreglo de las transiciones que va a tener el subconjunto
     */
    ArrayList<Transicion> transiciones = new ArrayList<Transicion>();
    
    /*Bandera que se utiliza en la creacion de AFD's*/
    boolean marcado = false;
    
    
    public Subset() {}
    
    /**
     * Este contructor recibe como argumentos una lista de nodos y una lista
     *  de transiciones.
     * @param nodos
     * @param transiciones 
     */
    public Subset(int nodos, ArrayList transiciones)
    {
       this.Nodos.add(nodos);
       this.transiciones = transiciones;
        
    }
    
    public Subset(ArrayList<Transicion> transiciones ){
        this.transiciones = transiciones;
    }

    /**
     * Devuele un ArrayList con todas las transiciones del subset
     * @return 
     */
    public ArrayList<Transicion> getTransiciones() {
        return transiciones;
    }

    
    public void setNodos(ArrayList<Integer> Nodos) {
        this.Nodos = Nodos;
    }

    /**
     * Devuelve un ArrayList<Integer> de los nodos del subset
     * @return 
     */
    public ArrayList<Integer> getNodos() {
        return Nodos;
    }

    /**
     * Añade un nuevo nodo al arrelgo de nodos del subset
     * @param nodoFinal 
     */
    public void add(int nodoFinal) {
        this.Nodos.add(nodoFinal);
        
    }
    
    
    
    /**
     * Este metodo combina dos eClosures, añadiendo los nodos del subset ingresado
     * al subset original.
     * @param subset1 
     */
    public void combinarEClosure(Subset subset1)
    {
        
        for(int i=0;i<subset1.getNodos().size();i++)
        {
            if(!this.Nodos.contains(subset1.getNodos().get(i)))
            {
                this.add(subset1.getNodos().get(i));
            }
            
        }
        
        
    }

    @Override
    public String toString() {
        return "Subset{" + "Nodos=" + Nodos+ '}';
    }
    
    
    public void eliminarRepetido()
    {
        Map<Integer,Subset> mapEstados = new HashMap<Integer, Subset>();
    }

    
    /**
     * Devuelve el largo de un Subset, basado en el largo del ArrayList de 
     * @return 
     */
    public int size()
    {
        return this.Nodos.size();
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }
    
    
    /**
     * Ordena el subconjunto de menor a mayor
     */
    public void ordenar()
    {
        Collections.sort(Nodos);
    }
    
}
