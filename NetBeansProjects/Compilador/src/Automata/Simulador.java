/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automata;

/**
 * Esta clase realiza la simulacion de un AFN
 * @author Moises Urias
 */
public class Simulador {
    
    private boolean cadenaAceptada; /*La cadena es aceptada por el atuomata*/
    
    private int estadoInicial;
    private int estadoAceptacion;
    
    private Automata automata; /*Automata que se estara simlando*/
    
    public Simulador(Automata automata)
    {
        this.automata = automata;
    }
    
    public boolean aceptacion(Automata automata, String cadena)
    {
        this.automata = automata;
        return cadenaAceptada;
    }
    
    /**
     * Este metodo hace la simulacion del AFN
     */
    public void simular(){
        /*Se encuentran los estados inciales y finales del automata*/
        this.estadoInicial = automata.getEstadoInicial();
        this.estadoAceptacion = automata.getEstadoFinal();
        
        /*Se genera un nuevo subset con el estado inicial del automata*/
        Subset subset = new Subset(estadoInicial, automata.getTransiciones());
        
        /*Se le realiza un eclosure al subset*/
        subset = OpExtra.eClosure(subset);
    }
    
    
}
