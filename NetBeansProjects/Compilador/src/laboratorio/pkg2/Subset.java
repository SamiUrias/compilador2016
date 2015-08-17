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
    ArrayList<Nodo> Nodo;
    ArrayList<Transicion> transiciones;
    
    public Subset() {}
    
    /**
     * Este contructor recibe como argumentos una lista de nodos y una lista
     *  de transiciones.
     * @param nodos
     * @param transiciones 
     */
    public Subset(ArrayList nodos, ArrayList transiciones)
    {
        System.out.println("Se ha creado un subset");
    }

    public ArrayList<Transicion> getTransiciones() {
        return transiciones;
    }

    
}
