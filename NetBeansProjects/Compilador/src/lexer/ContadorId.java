/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;

import Automata.ContadorNodo;

/**
 * Esta clase se utiliza para asignar con un valor consecutivo el Id de las
 *  palabras que se reservan al utilizar el HasmTable en el AnalizadorLexico.
 * 
 * @author Moises Urias
 */
public class ContadorId {
    private static int contador = 0; /*Contador del numero de nodos*/
    private static ContadorId contadorid = null;
    
    private ContadorId(){}
   
    public static synchronized ContadorId getInstance(){
        contador++; /*Aumenta en uno el contado de nodos*/
        
        if (contadorid == null)
        {
            contadorid = new ContadorId();
        }
        
        return contadorid;
    }
    
    public static int getContador(){
        return contador;
    }
}
