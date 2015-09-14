/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

/**
 * Esta clase modela una transicion especial para los AFD
 * @author eldonmoi
 */
public class TransicionAfd {
    
    private Subset estadoInicial;
    private Subset estadoFinal;
    private String simbolo;

    
    /**
     * Constructor vacio de las transiciones en el afd
     */
    public TransicionAfd() {
    
    }
    
    
    public TransicionAfd(Subset estadoInicial, String simbolo,Subset estado) {
        this.estadoInicial = estadoInicial;
        this.estadoFinal = estado;
        this.simbolo = simbolo;
    }

    public Subset getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Subset estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public Subset getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(Subset estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    
    @Override
    public String toString() {
        return "TransicionAFD{"  + estadoInicial + "," +simbolo+","+ estadoFinal + '}';
    }
    
    
    

    
    
            
    
}
