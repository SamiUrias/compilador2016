/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio.pkg2;

import java.util.ArrayList;
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
    ArrayList<Integer> Nodos = new ArrayList<Integer>();
    
    /**
     * Un ArrayList<Transicion> transiciones<p>
     * Contiene un arreglo de las transiciones que va a tener el subconjunto
     */
    ArrayList<Transicion> transiciones = new ArrayList<Transicion>();
    
    
    
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
        System.out.println("Se ha añadido un nodo al array de nodos");
        
    }

    /**
     * Devuele un ArrayList con todas las transiciones del subset
     * @return 
     */
    public ArrayList<Transicion> getTransiciones() {
        return transiciones;
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
        System.out.println("Se ha insertado el nodo: " + nodoFinal);
    }
    
    /**
     * Este metodo combina dos subsets, añadiendo los nodos del subset ingresado
     * al subset original.
     * @param subset1 
     */
    public void combinar(Subset subset1)
    {
        
        for(int i=0;i<subset1.getNodos().size();i++)
        {
            this.add(subset1.getNodos().get(i));
        }
    }

    @Override
    public String toString() {
        return "Subset{" + "Nodos=" + Nodos+ '}';
    }
    
    

    
}
