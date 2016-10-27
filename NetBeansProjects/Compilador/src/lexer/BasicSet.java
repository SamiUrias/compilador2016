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

    private boolean isString;
    private boolean isIdent;
    private boolean isChar;

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
        stringVerifier(cadena);
    }

    private void stringVerifier(String cadena){
        cadena = cadena.trim(); //Elimina espacios en blanco al principio y al final de la linea.
        String firstCharacter, lastCharacter;

        firstCharacter=String.valueOf(cadena.charAt(0));
        lastCharacter=String.valueOf(cadena.charAt(cadena.length()-1));

        System.out.println("FC: " + firstCharacter);
        System.out.println("LC: " + lastCharacter);


        if ((firstCharacter.equals("\"")) && (lastCharacter.equals("\""))){

        }
    }
}
