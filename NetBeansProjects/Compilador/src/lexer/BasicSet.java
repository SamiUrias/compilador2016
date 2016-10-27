/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;

/**
 *
 * @author Samuel
 */
public class BasicSet {

    /**
     * Constructor vacio.
     */
    public BasicSet(){

    }

    /**
     * Constructor con base en una cadena.
     * @param cadena
     */
    public BasicSet(String cadena){

    }

    private void stringVerifier(String cadena){
        String firstCharacter, lastCharacter;

        firstCharacter=cadena.substring(0,1);
        lastCharacter=cadena.substring(cadena.length()-1, cadena.length());

        System.out.println("FC: " + firstCharacter);
        System.out.println("LC: " + firstCharacter);
    }
}
