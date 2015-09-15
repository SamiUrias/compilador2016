/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import java.util.ArrayList;

/**
 * Esta clase modela un subset
 * @author Moises Urias
 */
public class S {
    
    /*Contiene todos los nodos del subset*/
    private ArrayList<Integer> nodos = new ArrayList<>();
    
    /*Transiciones*/
    private ArrayList<Transicion> transiciones = new ArrayList<>();
    
    
    /**
     * Se crea un subset inicial sin nodo, solamente usando las transiciones
     * @param transiciones
     */
    public S(ArrayList<Transicion> transiciones)
    {
        this.transiciones = transiciones;
    }
    
    
    /**
     * Se añade un nodo al arreglo de nodos
     * @param nodo 
     */
    public void agregarNodo(int nodo)
    {
       this.nodos.add(nodo);
    }
}
