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
    
    /**
     * Bandera que se utiliza en la creacion de AFD's
     * */
    boolean marcado = false;

    /**
     * Este atributo se utiliza en la construccion de AFD para ponerle nombre a cada subset.
     * Debido a que no se sabe cuantos estados pueda llegar a tener el AFD, entonces los 'nombres' se hacen con una
     * numeracion.
     */
    private int nombre_subset = 0;



    /**
     * Constructor vacio.
     * Este constructor no hace nada
     */

    public Subset() {}
    
    /**
     * Este contructor recibe como argumentos un nodo y una lista
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
     * Devuelve el largo de un Subset, basado en el largo del ArrayList de nodos
     * @return 
     */
    public int size()
    {
        return this.Nodos.size();
    }

    /**
     * Este metdo devuelve el atributo marcado del subset
     * @return boolean marcado
     */
    public boolean isMarcado() {
        return marcado;
    }

    /**
     * Este metodo marca este subset como que ya esta marcado
     */
    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }
    
    
    /**
     * Ordena el subconjunto de menor a mayor
     */
    public void ordenar() {
        Collections.sort(Nodos);
    }


    public int getNombre_subset() {
        return nombre_subset;
    }

    public void setNombre_subset(int nombre_subset) {
        this.nombre_subset = nombre_subset;
    }


    /**
     * Override el metodo equals.
     * Se comparan los subsets con base a los nodos que tenga.
     * */
    public boolean equals(Object obj){
        //Aquí se almacena la respuesta de si los objetos son iguales
        boolean sameObjects = false;
        if (obj instanceof Subset){
            Subset tmpSubset = (Subset)obj;
            tmpSubset.ordenar(); //Se ordenan los nodos que se van a comparar
            this.ordenar(); //Se ordenan los nodos actuales antes de compararlos
            if (this.Nodos.equals (tmpSubset.getNodos())){
                sameObjects = true;
            }
            else{
                sameObjects = false;
            }
        }

        return sameObjects;
    }
}
